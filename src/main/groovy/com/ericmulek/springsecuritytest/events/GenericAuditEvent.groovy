package com.ericmulek.springsecuritytest.events
//neat but gets overly verbose pretty quick
class GenericAuditEvent <T extends AuditEvent> {
    private final T what
    protected final boolean success

    GenericAuditEvent(T what, boolean success) {
        this.what = what
        this.success = success
    }
}
