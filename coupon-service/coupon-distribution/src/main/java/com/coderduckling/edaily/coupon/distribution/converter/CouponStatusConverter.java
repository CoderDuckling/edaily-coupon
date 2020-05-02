package com.coderduckling.edaily.coupon.distribution.converter;

import com.coderduckling.edaily.coupon.distribution.constant.CouponStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @ Description   :  优惠券状态枚举属性转换器
 * @ Author        :  CoderDuckling
 * @ CreateDate    :  2020/5/2 11:53
 * @ Version       :  1.0
 */

@Converter
public class CouponStatusConverter implements
        AttributeConverter<CouponStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(CouponStatus status) {
        return status.getCode();
    }

    @Override
    public CouponStatus convertToEntityAttribute(Integer code) {
        return CouponStatus.of(code);
    }
}
