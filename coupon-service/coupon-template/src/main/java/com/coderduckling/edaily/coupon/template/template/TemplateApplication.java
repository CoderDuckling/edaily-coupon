package com.coderduckling.edaily.coupon.template.template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ Description   :  模板服务启动类，其中：
 *              SpringBootApplication  启动服务
 *              EnableEurekaClient  开启发现注册客户端
 *              EnableScheduling   基于一定时间的操作服务
 *              EnableJpaAuditing  JPA自动注入功能
 * @ Author        :  CoderDuckling
 * @ CreateDate    :  2020/5/1 15:49
 * @ Version       :  1.0
 */

@SpringBootApplication
@EnableEurekaClient
@EnableScheduling
@EnableJpaAuditing
public class TemplateApplication {
    public static void main(String[] args) {
        SpringApplication.run(TemplateApplication.class,args);
    }
}
