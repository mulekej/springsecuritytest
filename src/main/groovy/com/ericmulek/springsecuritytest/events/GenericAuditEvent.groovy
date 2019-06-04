package com.ericmulek.springsecuritytest.events
//neat but gets overly verbose pretty quick
class GenericAuditEvent <T extends AuditEvent> {
    private final T what

    GenericAuditEvent(T what) {
        this.what = what
    }
}
