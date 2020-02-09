package com.imooc.coupon.repository;

import com.imooc.coupon.constant.CouponStatus;
import com.imooc.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * <p>
 *  Coupon Dao 定义
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-09 21:48
 */
public interface CouponDao extends JpaRepository<Coupon, Integer>,
        JpaSpecificationExecutor<Coupon> {

    /**
     * 根据 userId + 状态寻找优惠券记录
     *
     * @param userId
     * @param status
     * @return {@link Coupon}
     */
    List<Coupon> findAllByUserIdAndStatus(Long userId, CouponStatus status);


}
