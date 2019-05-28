package com.ericmulek.springsecuritytest.WebConfig

import com.ericmulek.springsecuritytest.controller.intercept.AuthenticateUserInterceptor
import com.ericmulek.springsecuritytest.controller.intercept.ValidateHeadersPresentInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig implements WebMvcConfigurer {

//    @Autowired
//    ValidateHeadersPresentInterceptor validateHeadersInterceptor
    @Autowired
    AuthenticateUserInterceptor certificateAuthInterceptor


    @Override
    void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ValidateHeadersPresentInterceptor()).addPathPatterns('/path4')
        registry.addInterceptor(certificateAuthInterceptor).addPathPatterns('/path3')
    }
}
