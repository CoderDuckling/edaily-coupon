package com.coderduckling.edaily.coupon.template.schedule;

import com.coderduckling.edaily.coupon.common.vo.TemplateRule;
import com.coderduckling.edaily.coupon.template.dao.CouponTemplateDao;
import com.coderduckling.edaily.coupon.template.entity.CouponTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ Description   :  定时任务
 * @ Author        :  CoderDuckling
 * @ CreateDate    :  2020/5/1 17:52
 * @ Version       :  1.0
 */

@Slf4j
@Component
public class ScheduledTask {

    private final CouponTemplateDao templateDao;
    @Autowired
    public ScheduledTask(CouponTemplateDao templateDao) {
        this.templateDao = templateDao;
    }

    /**
     * 下线已经过期的优惠券模板
     */
    @Scheduled(fixedRate = 60*60*1000)
    public void offlineCouponTemplate(){
        log.info("Start To Expire CouponTemplate");
        List<CouponTemplate> templates = templateDao.findAllByExpired(false);
        if(CollectionUtils.isEmpty(templates)){
            log.info("Done To Expire CouponTemplate.");
            return;
        }
        Date cur = new Date();
        List<CouponTemplate> expiredTemplates = new ArrayList<>(templates.size());

        templates.forEach(t ->{
            // 根据优惠券模板规则中的 "过期规则" 校验模板是否过期
            TemplateRule rule = t.getRule();
            if(rule.getExpiration().getDeadline() < cur.getTime()){
                t.setExpired(true);
                expiredTemplates.add(t);
            }
        });
        if (CollectionUtils.isNotEmpty(expiredTemplates)) {
            log.info("Expired CouponTemplate Num: {}",
                    templateDao.saveAll(expiredTemplates));
        }

        log.info("Done To Expire CouponTemplate.");
    }
}
