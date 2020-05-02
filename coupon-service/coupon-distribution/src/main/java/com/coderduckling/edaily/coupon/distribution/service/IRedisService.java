package com.coderduckling.edaily.coupon.distribution.service;


import com.coderduckling.edaily.coupon.common.exception.CouponException;
import com.coderduckling.edaily.coupon.distribution.entity.Coupon;

import java.util.List;

public interface IRedisService {

    //根据用户ID和状态查询缓存的优惠券列表数据
    //可能会返回 null, 代表从没有过记录
    List<Coupon> getCachedCoupons(Long userId,Integer status);

    //保存空的优惠券列表到缓存中心
    void saveEmptyCouponListToCache(Long userId,List<Integer> status);

    //尝试先从缓存中获取优惠券码
    String tryToAcquireCouponCodeFromCache(Integer templateId);

    //将优惠券码保存到Cache中
    //返回的为保存成功的个数
    Integer addCouponToCache(Long userId,List<Coupon> coupons,Integer status) throws CouponException;

}
