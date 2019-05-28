package com.ericmulek.springsecuritytest.controller.intercept

import com.ericmulek.springsecuritytest.service.MyAuthService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@Slf4j
class AuthenticateUserInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    MyAuthService authService
    @Autowired
    HttpServletRequest request

    @Override
    boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info('Step 2: Do some user Authentication')
        def newAttribute = authService.authenticateCert('clientSubject', 'clientIssuer')
        //if needed, set some value onto the request attrs
        request.setAttribute('someNewValue', new Object())

        if (!newAttribute) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, 'Your Cert was no good')
        }
        true
    }
}
