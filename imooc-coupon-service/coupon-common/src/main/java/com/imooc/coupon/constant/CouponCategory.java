package com.imooc.coupon.constant;

import com.imooc.coupon.exception.ParameterException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * <p>
 * 优惠券分类
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-08 23:30
 */
@Getter
@AllArgsConstructor
public enum CouponCategory {

    MANJIAN("满减券", "001"),
    ZHEKOU("折扣券", "002"),
    LIJIAN("立减券", "003");

    /**
     * 优惠券描述(分类)
     */
    private String description;

    /**
     * 优惠券分类编码
     */
    private String code;

    public static CouponCategory of(String code) {
        Objects.requireNonNull(code);
        return Stream.of(values())
                .filter(bean -> bean.code.equals(code))
                .findAny()
                .orElseThrow(() -> new ParameterException(code + "not exists!"));
    }

}
