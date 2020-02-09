package com.imooc.coupon.service;

import org.apache.kafka.clients.consumer.Consumer;

/**
 * <p>
 * Kafka 相关的服务接口定义
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-09 22:18
 */
public interface IKafkaService {

    /**
     * 消费优惠券， Kafka 消息
     * @param record {@link Consumer}
     */
    void consumeCouponKafkaMessage(Consumer<?, ?> record);

}
