package com.ericmulek.springsecuritytest.controller.intercept

import groovy.util.logging.Slf4j
import org.springframework.lang.Nullable
import org.springframework.stereotype.Component
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@Slf4j
class SerializedResponseInterceptor extends HandlerInterceptorAdapter {

    //todo figure out which is better for our purposes
    @Override
    void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        log.info("Step 7: get response ")
    }

    @Override
    void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
