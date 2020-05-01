package com.coderduckling.edaily.coupon.template.service;

import com.alibaba.fastjson.JSON;
import com.coderduckling.edaily.coupon.template.common.constant.CouponCategory;
import com.coderduckling.edaily.coupon.template.common.constant.DistributeTarget;
import com.coderduckling.edaily.coupon.template.common.constant.PeriodType;
import com.coderduckling.edaily.coupon.template.common.constant.ProductLine;
import com.coderduckling.edaily.coupon.template.common.vo.TemplateRule;
import com.coderduckling.edaily.coupon.template.template.service.IBuildTemplateService;
import com.coderduckling.edaily.coupon.template.template.vo.TemplateRequest;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

/**
 * @ Description   :  构造优惠券模板服务测试
 * @ Author        :  CoderDuckling
 * @ CreateDate    :  2020/5/1 18:46
 * @ Version       :  1.0
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class BuildTemplateTest {
    @Autowired
    private IBuildTemplateService buildTemplateService;

    @Test
    public void testBuildTemplate() throws Exception {

        System.out.println(JSON.toJSONString(
                buildTemplateService.buildTemplate(fakeTemplateRequest())
        ));
        Thread.sleep(5000);
    }

    private TemplateRequest fakeTemplateRequest() {

        TemplateRequest request = new TemplateRequest();
        request.setName("优惠券模板-" + new Date().getTime());
        request.setLogo("http://www.imooc.com");
        request.setDesc("这是一张优惠券模板");
        request.setCategory(CouponCategory.MANJIAN.getCode());
        request.setProductLine(ProductLine.DAMAO.getCode());
        request.setCount(10000);
        request.setUserId(10001L);  // fake user id
        request.setTarget(DistributeTarget.SINGLE.getCode());

        TemplateRule rule = new TemplateRule();
        rule.setExpiration(new TemplateRule.Expiration(
                PeriodType.SHIFT.getCode(),
                1, DateUtils.addDays(new Date(), 60).getTime()
        ));
        rule.setDiscount(new TemplateRule.Discount(5, 1));
        rule.setLimitation(1);
        rule.setUsage(new TemplateRule.Usage(
                "安徽省", "桐城市",
                JSON.toJSONString(Arrays.asList("文娱", "家居"))
        ));
        rule.setWeight(JSON.toJSONString(Collections.EMPTY_LIST));

        request.setRule(rule);

        return request;
    }
}
