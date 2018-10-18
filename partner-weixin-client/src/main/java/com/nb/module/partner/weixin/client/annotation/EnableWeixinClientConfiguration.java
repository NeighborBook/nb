package com.nb.module.partner.weixin.client.annotation;


import com.nb.module.partner.weixin.client.configuration.WeixinClientConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({WeixinClientConfiguration.class})
public @interface EnableWeixinClientConfiguration {
}
