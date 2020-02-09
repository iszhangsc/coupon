package com.imooc.coupon.constant;

import com.imooc.coupon.exception.ParameterException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.checkerframework.checker.units.qual.A;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * <p>
 * 分发目标
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-08 23:39
 */
@Getter
@AllArgsConstructor
public enum DistributeTarget {

    SINGLE("单用户", 1),
    MULTI("多用户", 2);

    /**
     * 分发目标描述
     */
    private String description;

    /**
     * 分发目标编码
     */
    private Integer code;


    public static DistributeTarget of(Integer code) {
        Objects.requireNonNull(code);
        return Stream.of(values())
                .filter(bean -> bean.code.equals(code))
                .findAny()
                .orElseThrow(() -> new ParameterException(code + "not exists!"));
    }

}
