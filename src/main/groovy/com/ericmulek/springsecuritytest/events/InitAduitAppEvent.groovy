package com.ericmulek.springsecuritytest.events

class InitAduitAppEvent extends GenericAuditEvent<InitialAuditEvent> {

    InitAduitAppEvent(InitialAuditEvent auditEvent) {
        super(auditEvent)
    }
}
