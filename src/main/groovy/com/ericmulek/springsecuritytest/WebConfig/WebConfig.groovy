package com.ericmulek.springsecuritytest.WebConfig

import com.ericmulek.springsecuritytest.controller.intercept.CertificateAuthInterceptor
import com.ericmulek.springsecuritytest.controller.intercept.ValidateHeadersInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig implements WebMvcConfigurer {

//    @Autowired
//    ValidateHeadersInterceptor validateHeadersInterceptor
    @Autowired
    CertificateAuthInterceptor certificateAuthInterceptor


    @Override
    void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ValidateHeadersInterceptor()).addPathPatterns('/path3')
        registry.addInterceptor(certificateAuthInterceptor).addPathPatterns('/path3')
    }
}
