package com.imooc.coupon.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.imooc.coupon.constant.CouponStatus;
import com.imooc.coupon.entity.Coupon;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import java.io.IOException;

/**
 * <p>
 *  优惠券状态枚举属性转换器
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-09 21:48
 */
@Convert
public class CouponStatusConverter implements AttributeConverter<CouponStatus, Integer> {


    @Override
    public Integer convertToDatabaseColumn(CouponStatus status) {
        return status.getCode();
    }

    @Override
    public CouponStatus convertToEntityAttribute(Integer code) {
        return CouponStatus.of(code);
    }

}
