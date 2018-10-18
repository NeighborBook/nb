package com.nb.module.partner.aliyun.annotation;


import com.nb.module.partner.aliyun.configuration.AliyunConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({AliyunConfiguration.class})
public @interface EnableAliyunConfiguration {
}
