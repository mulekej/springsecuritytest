package com.ericmulek.springsecuritytest.controller

import com.ericmulek.springsecuritytest.controller.annotations.VerifyFeature
import groovy.util.logging.Slf4j
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Slf4j
class TestRestController {

    @GetMapping('path3')
    @VerifyFeature(feature = 'SendMessageType3')
    String testEndpoint3() {
        log.info('testEndpoint3')
        'Hello Rest World'
    }
}
