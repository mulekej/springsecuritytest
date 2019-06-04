package com.ericmulek.springsecuritytest.events

import org.springframework.context.ApplicationEvent

//neat but gets overly verbose pretty quick
class GenericAuditEvent <T extends AuditEvent> extends ApplicationEvent{
    private final T what

//    GenericAuditEvent(T what) {
//        this.what = what
//    }
    /**
     * Create a new ApplicationEvent.
     * @param source the object on which the event initially occurred (never {@code null})
     */
    GenericAuditEvent(T auditEvent) {
        super(auditEvent)
    }
}
