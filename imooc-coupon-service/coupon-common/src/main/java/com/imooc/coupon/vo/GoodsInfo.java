package com.imooc.coupon.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *  商品信息
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-09 22:39
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsInfo {

    /**
     * 商品类型 {@link com.imooc.coupon.constant.GoodsType}
     */
    private Integer type;

    /**
     * 商品的价格
     */
    private Double price;

    /**
     * 商品的数量
     */
    private Integer count;

    // todo 名称、使用信息
}
