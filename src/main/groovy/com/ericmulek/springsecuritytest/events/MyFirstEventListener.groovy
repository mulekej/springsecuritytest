package com.ericmulek.springsecuritytest.events

import com.ericmulek.springsecuritytest.service.AuditTrailService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

import javax.servlet.http.HttpServletRequest
import javax.validation.constraints.NotNull

@Slf4j
@Component
class MyFirstEventListener {

    @Autowired
    HttpServletRequest request
    @Autowired
    AuditTrailService auditTrailService

    @EventListener
    void myFirstListener(@NotNull InitialAuditEvent event) {
        log.info("Listner1: Recieved Event from ${event.messageId}")
        auditTrailService.sendInitialAudit(event)
    }

    /*
    Returning an object from an annotated event listener results in
    that object going back onto the event bus. by returning the same object you got
    it sends the app into an infinite loop
     */
//    @EventListener
//    InitialAuditEvent infiniteLoopListener(@NotNull InitialAuditEvent event) {
//        log.info("Listner1: Recieved Event from ${event.messageId}")
//        auditTrailService.sendInitialAudit(event)
//        event
//    }

    //todo make separate class so there's no risk of accessing the httpServletRequest in the async
    @Async
    @EventListener
    void MyFollowupListener(@NotNull FollowupAuditEvent event) {
        log.info("Listner1: Recieved Event for ${event.auditId}")

        auditTrailService.appendToAuditTrail(event)
    }
}
