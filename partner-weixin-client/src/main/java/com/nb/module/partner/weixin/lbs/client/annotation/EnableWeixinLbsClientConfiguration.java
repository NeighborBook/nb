package com.nb.module.partner.weixin.lbs.client.annotation;


import com.nb.module.partner.weixin.lbs.client.configuration.WeixinLbsClientConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({WeixinLbsClientConfiguration.class})
public @interface EnableWeixinLbsClientConfiguration {
}
