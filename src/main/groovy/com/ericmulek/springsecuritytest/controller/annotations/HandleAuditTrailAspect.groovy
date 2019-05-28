package com.ericmulek.springsecuritytest.controller.annotations

import com.ericmulek.springsecuritytest.service.AuditTrailService
import groovy.util.logging.Slf4j
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

import javax.servlet.http.HttpServletRequest

@Aspect
@Component
@Slf4j
@Order(1)
class HandleAuditTrailAspect {

    @Autowired
    AuditTrailService auditTrailService
    @Autowired
    HttpServletRequest request

    @Before('@annotation(HandleAuditTrail)')
    void preprocessingPointCut() {
        log.info('Step 4: Create Audit Trail')
        def auditObject = new Object()
        auditTrailService.sendInitialAudit(auditObject)
    }

    @AfterReturning('@annotation(HandleAuditTrail)')
    void postProcessingPointCut() {
        log.info('Step 6: append Audit Data')
        def append = new Object()
        auditTrailService.appendToAuditTrail(append)
    }

    //only hit if no @ErrorHandler in place
    @AfterThrowing('@annotation(HandleAuditTrail)')
    void catchProcessingException() {
        def append = new Object()
        auditTrailService.appendToAuditTrail(append)
    }
}
