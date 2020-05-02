package com.coderduckling.edaily.coupon.distribution.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface IKafkaService {

    //消费优惠券   为了防止数据量过大，先存入消息队列后依次写入
    void consumeCouponKafkaMessage(ConsumerRecord<?, ?> record);
}
