package com.nb.module.partner.douban.client.annotation;


import com.nb.module.partner.douban.client.configuration.DoubanClientConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({DoubanClientConfiguration.class})
public @interface EnableDoubanClientConfiguration {
}
