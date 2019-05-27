package com.ericmulek.springsecuritytest.controller.annotations

import com.ericmulek.springsecuritytest.service.AuditTrailService
import org.aspectj.lang.annotation.Aspect
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.servlet.http.HttpServletRequest

@Aspect
@Component
class HandleAuditTrailAspect {

    @Autowired
    AuditTrailService auditTrailService

    @Autowired
    HttpServletRequest request

    void preprocessingPointCut() {
        def auditObject = new Object()
        auditTrailService.sendInitialAudit(auditObject)
    }

    void postProcessingPointCut() {
        def append = new Object()
        auditTrailService.appendToAuditTrail(append)
    }
}
