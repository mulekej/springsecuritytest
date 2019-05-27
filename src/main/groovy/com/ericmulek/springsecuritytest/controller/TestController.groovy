package com.ericmulek.springsecuritytest.controller

import com.ericmulek.springsecuritytest.controller.annotations.VerifyFeature
import groovy.util.logging.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Slf4j
@Controller
class TestController {

    @GetMapping('path1')
    @VerifyFeature(feature = 'SendMessageType1')
    @ResponseBody
    String testEndpoint(){
        log.info('testEndpoint')
        'Hello World'
    }

    @GetMapping('path2')
    @VerifyFeature(feature = 'SendMessageType2')
    ResponseEntity<String> testEndpoint2(){
        log.info('testEndpoint2')
        new ResponseEntity<String>('Hello World', HttpStatus.OK)
    }

    @GetMapping('badPath')
    String testBadEndpoint() {
        log.info('BAD ENDPOINT PATH')
        'Bad Endpoint Setup'
    }
}
