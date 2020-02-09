package com.imooc.coupon.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.net.util.IPAddressUtil;

import java.util.List;

/**
 * <p>
 * 结算信息对象定义
 * userId
 * 商品信息
 * 优惠券列表
 * 结算结果金额
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-09 22:42
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettlementInfo {
    private Long userId;

    private List<CouponAndTemplateInfo> couponAndTemplateInfos;

    private List<GoodsInfo> goodsInfos;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CouponAndTemplateInfo {
        /**
         * coupon 的主键
         */
        private Integer id;

        private CouponTemplateSDK templateSDK;
    }

}
