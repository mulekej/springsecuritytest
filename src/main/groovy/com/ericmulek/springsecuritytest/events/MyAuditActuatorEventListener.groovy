package com.ericmulek.springsecuritytest.events

import groovy.util.logging.Slf4j
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

import javax.validation.constraints.NotNull

@Slf4j
@Component
class MyAuditActuatorEventListener {

    @Async
    @EventListener
    void myFirstListener(@NotNull InitialAuditEvent event) {
        sleep(3000)
        log.info('Sometime Later...')
        log.info("Step4b: Recieved Event from ${event.messageId}")
    }

    @Async
    @EventListener
    void myFirstListener(@NotNull FollowupAuditEvent event) {
        log.info("Listner2: Recieved Follow upEvent for ${event.auditId}")
    }
}
