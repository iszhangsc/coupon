package com.imooc.coupon.constant;

import java.time.format.SignStyle;

/**
 * <p>
 * 通用常量定义
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-09 11:36
 */
public class Constants {

    /**
     * Kafka 消息的 Topic
     */
    public static final String TOPIC = "imooc_user_coupon_op";


    /**
     * <p>
     * Redis key 前缀定义
     * </P>
     */
    public static class RedisPrefix {

        /**
         * 优惠券 key 前缀
         */
        public static final String COUPON_TEMPLATE = "imooc_coupon_template_code_";

        /**
         * 用户当前所有已使用的优惠券 key 前缀
         */
        public static final String USER_COUPON_USABLE = "imooc_user_coupon_used_";

        /**
         * 用户当前所有已过期的优惠券 key 前缀
         */
        public static final String USER_COUPON_EXPIRED = "imooc_user_coupon_expired_";

    }

}
