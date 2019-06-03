package com.ericmulek.springsecuritytest.controller.annotations

import com.ericmulek.springsecuritytest.events.InitialAuditEvent
import groovy.util.logging.Slf4j
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

import javax.servlet.http.HttpServletRequest

@Aspect
@Component
@Slf4j
@Order(1)
class HandleAuditTrailAspect {

    @Autowired
    HttpServletRequest request
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher


    @Before('@annotation(HandleAuditTrail)')
    void preprocessingPointCut() {
        log.info('Step 4: Create Audit Trail')
        def auditObject = new InitialAuditEvent(messageId: '12345', senderIp: '1.2.3.4.5', senderName: 'PersonsName')
        applicationEventPublisher.publishEvent(auditObject)
    }

    @AfterReturning('@annotation(HandleAuditTrail)')
    void postProcessingPointCut() {
        log.info('Step 6: append Audit Data')
        def append = new Object()
    }

    //only hit if no @ErrorHandler in place
    @AfterThrowing('@annotation(HandleAuditTrail)')
    void catchProcessingException() {
        def append = new Object()
    }
}
