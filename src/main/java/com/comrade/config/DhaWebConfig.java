package com.comrade.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.beans.BeanProperty;

@Configuration
public class DhaWebConfig implements WebMvcConfigurer {

    @BeanProperty
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addFormatters(FormatterRegistry registry) {
                registry.addConverter(new EntityTypeToEnumConverter());
            }
        };
    }
}
