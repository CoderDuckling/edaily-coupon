package com.coderduckling.edaily.coupon.distribution.service.impl;

import com.alibaba.fastjson.JSON;
import com.coderduckling.edaily.coupon.common.constant.Constant;
import com.coderduckling.edaily.coupon.distribution.constant.CouponStatus;
import com.coderduckling.edaily.coupon.distribution.dao.CouponDao;
import com.coderduckling.edaily.coupon.distribution.entity.Coupon;
import com.coderduckling.edaily.coupon.distribution.service.IKafkaService;
import com.coderduckling.edaily.coupon.distribution.vo.CouponKafkaMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @ Description   :  是将 Cache 中的 Coupon 的状态变化同步到 DB 中
 * @ Author        :  CoderDuckling
 * @ CreateDate    :  2020/5/3 22:20
 * @ Version       :  1.0
 */

@Slf4j
@Service
public class KafkaServiceImpl implements IKafkaService {

    private final CouponDao couponDao;

    @Autowired
    public KafkaServiceImpl(CouponDao couponDao) {
        this.couponDao = couponDao;
    }

    /**
     * 消费优惠券 Kafka 消息
     * @param record
     */
    @Override
    @KafkaListener(topics = {Constant.TOPIC}, groupId = "edaily-coupon-1")
    public void consumeCouponKafkaMessage(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        //如果kafka中有消息
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            CouponKafkaMessage couponInfo = JSON.parseObject(
                    message.toString(),
                    CouponKafkaMessage.class
            );
            log.info("Receive CouponKafkaMessage: {}", message.toString());
            CouponStatus status = CouponStatus.of(couponInfo.getStatus());
            switch (status) {
                case USABLE:
                    break;
                case USED:
                    processUsedCoupons(couponInfo, status);
                    break;
                case EXPIRED:
                    processExpiredCoupons(couponInfo, status);
                    break;
            }
        }
    }
        /**
         * 处理已使用的用户优惠券
         * */
        private void processUsedCoupons (CouponKafkaMessage kafkaMessage, CouponStatus status){
            // TODO 给用户发送短信
            processCouponsByStatus(kafkaMessage, status);
        }

        /**
         * 处理过期的用户优惠券
         * */
        private void processExpiredCoupons (CouponKafkaMessage kafkaMessage, CouponStatus status){
            // TODO 给用户发送推送
            processCouponsByStatus(kafkaMessage, status);
        }

        /**
         * 根据状态处理优惠券信息
         * */
        private void processCouponsByStatus (CouponKafkaMessage kafkaMessage, CouponStatus status){
            List<Coupon> coupons = couponDao.findAllById(
                    kafkaMessage.getIds()
            );
            if (CollectionUtils.isEmpty(coupons)
                    || coupons.size() != kafkaMessage.getIds().size()) {
                log.error("Can Not Find Right Coupon Info: {}",
                        JSON.toJSONString(kafkaMessage));
                // TODO 发送邮件
                return;
            }

            coupons.forEach(c -> c.setStatus(status));
            log.info("CouponKafkaMessage Op Coupon Count: {}",
                    couponDao.saveAll(coupons).size());
        }
}
