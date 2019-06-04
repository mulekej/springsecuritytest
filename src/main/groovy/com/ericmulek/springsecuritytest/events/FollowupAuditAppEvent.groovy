package com.ericmulek.springsecuritytest.events

class FollowupAuditAppEvent extends GenericAuditEvent<FollowupAuditEvent> {

    FollowupAuditAppEvent(FollowupAuditEvent what) {
        super(what)
    }
}
