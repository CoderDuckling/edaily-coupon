package com.coderduckling.edaily.coupon.template.template.vo;

import com.coderduckling.edaily.coupon.template.common.constant.CouponCategory;
import com.coderduckling.edaily.coupon.template.common.constant.DistributeTarget;
import com.coderduckling.edaily.coupon.template.common.constant.ProductLine;
import com.coderduckling.edaily.coupon.template.common.vo.TemplateRule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @ Description   :  优惠券模板创建请求对象
 * @ Author        :  CoderDuckling
 * @ CreateDate    :  2020/5/1 17:27
 * @ Version       :  1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateRequest {

    /** 优惠券名称 */
    private String name;

    /** 优惠券 logo */
    private String logo;

    /** 优惠券描述 */
    private String desc;

    /** 优惠券分类 */
    private String category;

    /** 产品线 */
    private Integer productLine;

    /** 总数 */
    private Integer count;

    /** 创建用户 */
    private Long userId;

    /** 目标用户 */
    private Integer target;

    /** 优惠券规则 */
    private TemplateRule rule;

    /**
     * <h2>校验对象的合法性</h2>
     * */
    public boolean validate() {

        boolean stringValid = StringUtils.isNotEmpty(name)
                && StringUtils.isNotEmpty(logo)
                && StringUtils.isNotEmpty(desc);
        boolean enumValid = null != CouponCategory.of(category)
                && null != ProductLine.of(productLine)
                && null != DistributeTarget.of(target);
        boolean numValid = count > 0 && userId > 0;

        return stringValid && enumValid && numValid && rule.validate();
    }
}