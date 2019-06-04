package com.ericmulek.springsecuritytest.events

class InitAduitAppEvent extends GenericAuditEvent<InitialAuditEvent> {

    InitAduitAppEvent(InitialAuditEvent what) {
        super(what)
    }
}
