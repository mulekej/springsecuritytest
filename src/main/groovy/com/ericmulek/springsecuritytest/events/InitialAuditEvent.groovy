package com.ericmulek.springsecuritytest.events

class InitialAuditEvent implements AuditEvent {

    String messageId
    String senderIp
    String senderName
}
