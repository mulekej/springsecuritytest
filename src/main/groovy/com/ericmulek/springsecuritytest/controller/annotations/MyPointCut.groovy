package com.ericmulek.springsecuritytest.controller.annotations

import groovy.util.logging.Slf4j
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

@Aspect
@Component
@Slf4j
class MyPointCut {

    @Before('@annotation(VerifyFeature)')
    void doThingHere() {
        log.info('in pointcut')
        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, 'Ya dun goofed')
    }
}
