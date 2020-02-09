package com.imooc.coupon.constant;

import com.imooc.coupon.exception.ParameterException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * <p>
 * 用户优惠券的状态
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-09 19:20
 */
@Getter
@AllArgsConstructor
public enum CouponStatus {

    USABLE("可用", 1),
    USED("已使用的", 2),
    EXPIRED("过期的(未被使用的)", 3);

    /**
     * 优惠券状态描述信息
     */
    private String description;

    /**
     * 优惠券状态编码
     */
    private Integer code;

    /**
     * 根据code 获取 CouponStatus
     * @param code
     * @return
     */
    public static CouponStatus of(Integer code) {
        Objects.requireNonNull(code);
        return Stream.of(values())
                .filter(o -> o.code.equals(code))
                .findAny()
                .orElseThrow(() -> new ParameterException(code + "not exist!"));
    }

}
