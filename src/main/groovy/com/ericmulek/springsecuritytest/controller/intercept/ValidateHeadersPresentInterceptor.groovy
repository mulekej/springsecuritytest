package com.ericmulek.springsecuritytest.controller.intercept

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Slf4j
class ValidateHeadersPresentInterceptor extends HandlerInterceptorAdapter {


    @Autowired
    HttpServletRequest request

    @Override
    boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info('Step 1: Validating Headers are present')
        //get request headers

        //validate headers present
        true
    }
}
