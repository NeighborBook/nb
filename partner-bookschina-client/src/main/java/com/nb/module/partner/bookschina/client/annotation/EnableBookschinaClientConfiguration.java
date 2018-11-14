package com.nb.module.partner.bookschina.client.annotation;


import com.nb.module.partner.bookschina.client.configuration.BookschinaClientConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({BookschinaClientConfiguration.class})
public @interface EnableBookschinaClientConfiguration {
}
