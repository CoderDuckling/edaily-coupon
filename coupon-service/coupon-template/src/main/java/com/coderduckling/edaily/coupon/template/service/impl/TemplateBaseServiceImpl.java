package com.coderduckling.edaily.coupon.template.service.impl;

import com.coderduckling.edaily.coupon.common.exception.CouponException;
import com.coderduckling.edaily.coupon.common.vo.CouponTemplateSDK;
import com.coderduckling.edaily.coupon.template.dao.CouponTemplateDao;
import com.coderduckling.edaily.coupon.template.entity.CouponTemplate;
import com.coderduckling.edaily.coupon.template.service.ITemplateBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TemplateBaseServiceImpl implements ITemplateBaseService {

    private final CouponTemplateDao templateDao;

    @Autowired
    public TemplateBaseServiceImpl(CouponTemplateDao templateDao) {
        this.templateDao = templateDao;
    }

    /**
     * 根据优惠券模板 id 获取优惠券模板信息
     * @param id
     * @return
     * @throws CouponException
     */
    @Override
    public CouponTemplate buildTemplateInfo(Integer id) throws CouponException {
        /**
         * Optional  主要是用于接收到参数后方便  判空
         */
        Optional<CouponTemplate> template = templateDao.findById(id);
        if(!template.isPresent()){
            throw new CouponException("Template Is Not Exist: " + id);
        }
        return template.get();
    }

    /**
     * 查询所有可用模板
     * @return
     */
    @Override
    public List<CouponTemplateSDK> findAllUsableTemplate() {
        List<CouponTemplate> templates =
                templateDao.findAllByAvailableAndExpired(
                        true, false);

        return templates.stream()
                .map(this::template2TemplateSDK).collect(Collectors.toList());
    }

    /**
     * 获取模板 ids 到 CouponTemplateSDK 的映射
     * @param ids
     * @return
     */
    @Override
    public Map<Integer, CouponTemplateSDK> findIds2TemplateSDK(Collection<Integer> ids) {
        List<CouponTemplate> templates = templateDao.findAllById(ids);

        return templates.stream().map(this::template2TemplateSDK)
                .collect(Collectors.toMap(
                        CouponTemplateSDK::getId, Function.identity()
                ));
    }

    /**
     * 将 CouponTemplate 转换为 CouponTemplateSDK
     * @param template
     * @return
     */
    private CouponTemplateSDK template2TemplateSDK(CouponTemplate template) {

        return new CouponTemplateSDK(
                template.getId(),
                template.getName(),
                template.getLogo(),
                template.getDesc(),
                template.getCategory().getCode(),
                template.getProductLine().getCode(),
                template.getKey(),  // 并不是拼装好的 Template Key
                template.getTarget().getCode(),
                template.getRule()
        );
    }
}
