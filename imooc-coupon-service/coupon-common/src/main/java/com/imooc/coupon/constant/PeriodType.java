package com.imooc.coupon.constant;

import com.imooc.coupon.exception.ParameterException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * <p>
 *  有效期类型枚举
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-08 23:45
 */
@Getter
@AllArgsConstructor
public enum PeriodType {

    REGULAR("固定的(固定日期)", 1),
    SHIFT("变动的(以领取之日开始计算)", 2);

    /**
     * 有效期描述
     */
    private String description;

    /**
     * 有效期编码
     */
    private Integer code;

    public static PeriodType of(Integer code) {
        Objects.requireNonNull(code);
        return Stream.of(values())
                .filter(bean -> bean.code.equals(code))
                .findAny()
                .orElseThrow(() -> new ParameterException(code + "not exists!"));
    }

}
