package com.imooc.coupon.vo;

import com.imooc.coupon.constant.CouponCategory;
import com.imooc.coupon.constant.DistributeTarget;
import com.imooc.coupon.constant.ProductLine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * 优惠券模板创建请求对象
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-09 10:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateRequest {

    /**
     * 优惠券名称
     */
    private String name;

    /**
     * 优惠券 logo
     */
    private String logo;

    /**
     * 优惠券描述
     */
    private String intro;

    /**
     * 优惠券分类
     */
    private String category;

    /**
     * 产品线
     */
    private Integer productLine;

    /**
     * 总数
     */
    private Integer couponCount;

    /**
     * 创建用户
     */
    private Long userId;

    /**
     * 目标用户
     */
    private Integer target;

    /**
     * 规则
     */
    private TemplateRule rule;

    /**
     * 校验对象的合法性
     * @return boolean
     */
    public boolean validate() {
        boolean stringValid = StringUtils.isNotEmpty(name)
                && StringUtils.isNotEmpty(logo)
                && StringUtils.isNotEmpty(intro);

        boolean enumValid = CouponCategory.of(category) != null
                && ProductLine.of(productLine) != null
                && DistributeTarget.of(target) != null;

        boolean numValid = couponCount > 0
                && userId > 0;

        return stringValid && enumValid && numValid && rule.validate();
    }

}
