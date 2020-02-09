package com.imooc.coupon.service;

import com.imooc.coupon.entity.Coupon;
import com.imooc.coupon.exception.CouponException;
import com.imooc.coupon.vo.AcquireTemplateRequest;
import com.imooc.coupon.vo.CouponTemplateSDK;

import java.util.List;

/**
 * <p>
 * 用户服务相关的接口定义
 *      1.用户三类状态优惠券信息展示服务
 *      2.查看用户当前可以领取的优惠券模板 coupon-template 微服务配合实现
 *      3.用户领取优惠券服务
 *      4.用户消费优惠券服务 coupon-settlement 微服务配合实现
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-09 22:21
 */
public interface IUserService {

    /**
     * 根据 用户Id 和 状态查询优惠券记录
     *
     * @param userId 用户ID
     * @param status
     * @return {@link Coupon}
     * @throws CouponException
     */
    List<Coupon> findCouponsByStatus(Long userId, Integer status) throws CouponException;

    /**
     * 根据用户ID查找当前可以领取的优惠券模板
     *
     * @param userId
     * @return {@link CouponTemplateSDK}
     */
    List<CouponTemplateSDK> findAvailableTemplate(Long userId) throws CouponException;

    /**
     * 用户领取优惠券
     * @param request {@link AcquireTemplateRequest}
     * @return {@link Coupon}
     * @throws CouponException
     */
    Coupon acquireTemplate(AcquireTemplateRequest request) throws CouponException;


}
