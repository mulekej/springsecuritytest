package com.ericmulek.springsecuritytest.controller.intercept

import com.ericmulek.springsecuritytest.service.MyAuthService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@Slf4j
class CertificateAuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    MyAuthService authService

    @Override
    boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info('CERT CHECK')
        authService.authenticateCert('clientSubject', 'clientIssuer')
    }
}
