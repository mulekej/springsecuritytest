package com.ericmulek.springsecuritytest.controller.annotations

import com.ericmulek.springsecuritytest.events.FollowupAuditEvent
import com.ericmulek.springsecuritytest.events.InitialAuditEvent
import groovy.util.logging.Slf4j
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
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

    //Not sure why GetMapping needed to be fully qualified, wouldn't build without it though
    @Around('@target(AuditTrailEnabled) && @annotation(org.springframework.web.bind.annotation.PostMapping)')
    Object handleAuditing(ProceedingJoinPoint joinPoint) {
        log.info('Step 4: Create Audit Trail')
        def auditObject = new InitialAuditEvent(messageId: '12345', senderIp: '1.2.3.4.5', senderName: 'PersonsName')
        applicationEventPublisher.publishEvent(auditObject)
        def responsePayload = joinPoint.proceed()
        log.info('Step 6: append Audit Data')
        responsePayload
    }

    @Around('@target(AuditTrailEnabled) && @annotation(org.springframework.web.bind.annotation.ExceptionHandler)')
    Object handleExceptionAuditing(ProceedingJoinPoint joinPoint) {
        def args = joinPoint.args
        FollowupAuditEvent event = buildUpSomeContextBasedOnExceptionType(args)
        def responsePayload = joinPoint.proceed()
        def auditObjectOfFailure = new FollowupAuditEvent(auditId: 'someAuditId')
        applicationEventPublisher.publishEvent(auditObjectOfFailure)
        log.info('Step 6b: error handler', )
        responsePayload
    }

    FollowupAuditEvent buildUpSomeContextBasedOnExceptionType(Object[] arguments) {
        null
    }

}
