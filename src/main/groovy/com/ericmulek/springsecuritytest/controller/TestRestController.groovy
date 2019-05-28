package com.ericmulek.springsecuritytest.controller

import com.ericmulek.springsecuritytest.controller.annotations.HandleAuditTrail
import com.ericmulek.springsecuritytest.controller.annotations.VerifyFeature
import com.ericmulek.springsecuritytest.domain.Person
import groovy.util.logging.Slf4j
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@Slf4j
class TestRestController {

    @GetMapping('path3')
    @VerifyFeature(feature = 'SendMessageType3')
    @HandleAuditTrail
    String testEndpoint3() {
        log.info('testEndpoint3')
        'Hello Rest World'
    }

    @PostMapping('path4')
    @VerifyFeature(feature = 'SendMessageType3')
    @HandleAuditTrail
    String acceptPerson(@RequestBody Person person) {
        log.info("Step 5: In Controller. $person.firstName - $person.lastName")
        'My Response String'
    }
}
