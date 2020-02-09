package com.imooc.coupon.constant;

import com.imooc.coupon.exception.ParameterException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * <p>
 *  商品类型描述
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-09 22:33
 */
@Getter
@AllArgsConstructor
public enum GoodsType {

    WENYU("文娱", 1),
    SHENGXIAN("生鲜", 2),
    JIAJU("家居", 3),
    OTHERS("其他", 4),
    ALL("全品类", 5);

    private String description;

    private Integer code;

    public GoodsType of(Integer code) {
        return Stream.of(values())
                .filter(o -> o.code.equals(code))
                .findAny()
                .orElseThrow(() -> new ParameterException(code + "Is Not Exist"));
    }

}
