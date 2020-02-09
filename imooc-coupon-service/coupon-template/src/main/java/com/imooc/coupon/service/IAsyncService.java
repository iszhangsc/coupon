package com.imooc.coupon.service;

import com.imooc.coupon.entity.CouponTemplate;

/**
 * <p>
 * 异步服务接口定义
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-09 11:19
 */
public interface IAsyncService {

    /**
     * 根据模板异步的创建优惠券码
     * @param template {@link CouponTemplate}  优惠券模板实体
     */
    void asyncConstructCouponByTemplate(CouponTemplate template);

}
