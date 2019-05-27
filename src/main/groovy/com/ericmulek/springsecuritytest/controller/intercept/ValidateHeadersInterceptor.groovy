package com.ericmulek.springsecuritytest.controller.intercept

import groovy.util.logging.Slf4j
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Slf4j
class ValidateHeadersInterceptor extends HandlerInterceptorAdapter {

    @Override
    boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info('VALIDATE HEADER')
        true
    }
}
