package com.ericmulek.springsecuritytest.controller.annotations

import com.ericmulek.springsecuritytest.service.MyAuthService
import groovy.util.logging.Slf4j
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

import javax.servlet.http.HttpServletRequest
import java.lang.reflect.Method

@Aspect
@Component
@Slf4j
class VerifyFeatureAspect {

    @Autowired
    MyAuthService authService
    @Autowired
    HttpServletRequest request

    //todo figure out how to included the annotation in the method args
    @Before('@annotation(VerifyFeature)')
    void doThingHere(JoinPoint joinPoint) {
        String feature = extractFeature(joinPoint)
        log.info("in pointcut feature=$feature")
        if(!authService.verifyFeature(feature)) {
            throw new ResponseStatusException(HttpStatus.OK, 'Feature Not Excepted')
        }
    }

    private String extractFeature(JoinPoint joinPoint) {
        MethodSignature methodSignature = joinPoint.getSignature() as MethodSignature
        Method method = methodSignature.getMethod()
        VerifyFeature annotation = method.getAnnotation(VerifyFeature)
        String feature = annotation.feature()
        feature
    }
}
