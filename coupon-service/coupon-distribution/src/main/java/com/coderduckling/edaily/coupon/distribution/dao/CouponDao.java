package com.coderduckling.edaily.coupon.distribution.dao;

import com.coderduckling.edaily.coupon.distribution.constant.CouponStatus;
import com.coderduckling.edaily.coupon.distribution.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CouponDao extends JpaRepository<Coupon, Integer> {

    /**
     * 根据 userId + 状态寻找优惠券记录
     * where userId = ... and status = ...
     * */
    List<Coupon> findAllByUserIdAndStatus(Long userId, CouponStatus status);
}
