package com.coderduckling.edaily.coupon.template.template.service;

import com.coderduckling.edaily.coupon.template.common.exception.CouponException;
import com.coderduckling.edaily.coupon.template.common.vo.CouponTemplateSDK;
import com.coderduckling.edaily.coupon.template.template.entity.CouponTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @ Description   :  优惠券基础服务定义，CRUD
 * @ Author        :  CoderDuckling
 * @ CreateDate    :  2020/5/1 17:31
 * @ Version       :  1.0
 */

public interface ITemplateBaseService {
    /**
     * 根据优惠券模板 id 获取优惠券模板信息
     * @param id 模板 id
     * @return {@link CouponTemplate} 优惠券模板实体
     */
    CouponTemplate buildTemplateInfo(Integer id) throws CouponException;

    /**
     * 查询所有可用模板
     * <h2>查找所有可用的优惠券模板</h2>
     * @return {@link CouponTemplateSDK}s
     */
    List<CouponTemplateSDK> findAllUsableTemplate();

    /**
     * 获取模板ids到CouponTemplateSDK的映射
     * @param ids 模板 ids
     * @return Map<key: 模板 id， value: CouponTemplateSDK>
     */
    Map<Integer,CouponTemplateSDK> findIds2TemplateSDK(Collection<Integer> ids);

}
