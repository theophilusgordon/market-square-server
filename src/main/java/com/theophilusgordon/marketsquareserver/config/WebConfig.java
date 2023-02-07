package com.theophilusgordon.marketsquareserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    private LocalValidatorFactoryBean validator;

    @Override
    public LocalValidatorFactoryBean getValidator() {
        if (validator == null) {
            validator = new LocalValidatorFactoryBean();
        }
        return validator;
    }
}
