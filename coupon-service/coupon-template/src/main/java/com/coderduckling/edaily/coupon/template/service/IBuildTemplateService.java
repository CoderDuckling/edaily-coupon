package com.coderduckling.edaily.coupon.template.service;

import com.coderduckling.edaily.coupon.common.exception.CouponException;
import com.coderduckling.edaily.coupon.template.entity.CouponTemplate;
import com.coderduckling.edaily.coupon.template.vo.TemplateRequest;

/**
 * @ Description   :  构建优惠券模板接口定义
 * @ Author        :  CoderDuckling
 * @ CreateDate    :  2020/5/1 17:30
 * @ Version       :  1.0
 */

public interface IBuildTemplateService {
    /**
     * 创建优惠券模板
     * @param request {@link TemplateRequest} 模板信息请求对象
     * @return {@link CouponTemplate} 优惠券模板实体
     */
    CouponTemplate buildTemplate(TemplateRequest request) throws CouponException;
}
