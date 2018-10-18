package com.nb.module.partner.szmesoft.client.annotation;


import com.nb.module.partner.szmesoft.client.configuration.SzmesoftClientConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({SzmesoftClientConfiguration.class})
public @interface EnableSzmesoftClientConfiguration {
}
