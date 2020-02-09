package com.imooc.coupon.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 获取优惠券请求对象定义
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-09 22:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcquireTemplateRequest {

    private Long userId;

    private CouponTemplateSDK templateSDK;

}