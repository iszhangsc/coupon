package com.imooc.coupon.service;

import com.imooc.coupon.entity.Coupon;
import com.imooc.coupon.exception.CouponException;

import java.util.List;

/**
 * <p>
 * redis 相关的操作服务接口定义
 * 1.用户的优惠券三个状态
 * 2.优惠券模板生成的优惠券码
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-09 22:08
 */
public interface IRedisService {

    /**
     * 根据 userId 和状态找到和缓存的优惠券列表数据
     *
     * @param userId 用户ID
     * @param status 优惠券状态 {@link com.imooc.coupon.constant.CouponStatus}
     * @return {@link Coupon} ,null->没有记录
     */
    List<Coupon> getCachedCoupons(Long userId, Integer status);

    /**
     * 保存空的 优惠券列表到 缓存中
     *
     * @param userId 用户ID
     * @param status 优惠券状态
     */
    void saveEmptyCouponListToCache(Long userId, List<Integer> status);


    /**
     * 尝试从 Cache 中获取一个优惠券码
     *
     * @param templateId 模板ID
     * @return String
     */
    String tryToAcquireCouponCodeFromCache(Integer templateId);

    /**
     * 添加优惠券到缓存中
     *
     * @param userId  用户ID
     * @param coupons {@link Coupon}优惠券列表
     * @param status  优惠券状态
     * @return 保存成功的个数
     * @throws CouponException
     */
    Integer addCouponToCache(Long userId, List<Coupon> coupons, Integer status) throws CouponException;

}
