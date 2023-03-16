package com.theophilusgordon.marketsquareserver.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
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
