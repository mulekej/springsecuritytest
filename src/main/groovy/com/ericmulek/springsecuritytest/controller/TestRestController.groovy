package com.ericmulek.springsecuritytest.controller

import com.ericmulek.springsecuritytest.controller.annotations.AuditTrailEnabled
import com.ericmulek.springsecuritytest.controller.annotations.PostFilter
import com.ericmulek.springsecuritytest.controller.annotations.VerifyFeature
import com.ericmulek.springsecuritytest.domain.Person
import com.ericmulek.springsecuritytest.service.AnExternalService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@Slf4j
@AuditTrailEnabled
class TestRestController {

    @Autowired
    AnExternalService externalService
    @Autowired
    Map threadProperties

    @GetMapping('path3')
    @VerifyFeature(feature = 'SendMessageType3')
    String testEndpoint3() {
        threadProperties['bob'] = 'hello'
        log.info(externalService.thingsFromOtherServices.join(';'))
        'Hello Rest World'
    }

    @PostMapping('path4')
    @VerifyFeature(feature = 'SendMessageType3')
    String acceptPerson(@RequestBody Person person) {
        log.info("Step 5: In Controller. $person.firstName - $person.lastName")
        'My Response String'
    }

    @GetMapping('path5')
    @VerifyFeature(feature = 'SendMessageType3')
    @PostFilter
    List<Person> getPerson() {

        externalService.getPeople()
    }

    @ExceptionHandler
    String errorHandler(Exception e) {
        log.info("Handling Exception ${e.class}")
        'You sent a bad request'
    }
}
