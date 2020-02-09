package com.imooc.coupon.converter;

import com.imooc.coupon.constant.CouponCategory;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

/**
 * <p>
 * 优惠券分类枚举类属性转换器
 * AttributeConverter<X,Y>
 *     X:是实体属性的类型
 *     Y:是数据库字段的类型
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-09 9:54
 */
@Convert
public class CouponCategoryConverter
        implements AttributeConverter<CouponCategory, String> {

    /**
     * <h2>将实体属性X转换为Y存储到数据库中, 插入和更新时执行的动作</h2>
     * @param couponCategory 实体属性
     * @return
     */
    @Override
    public String convertToDatabaseColumn(CouponCategory couponCategory) {
        return couponCategory.getCode();
    }

    /**
     * 将数据库中的字段Y转换为实体属性X,查询操作时执行的动作.
     * @param code
     * @return
     */
    @Override
    public CouponCategory convertToEntityAttribute(String code) {
        return CouponCategory.of(code);
    }

}
