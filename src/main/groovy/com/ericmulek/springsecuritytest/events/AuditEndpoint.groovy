package com.ericmulek.springsecuritytest.events

import org.springframework.boot.actuate.endpoint.annotation.Endpoint
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation
import org.springframework.stereotype.Component

@Component
@Endpoint(id = 'myAuditEndpoint')
class AuditEndpoint {

    //see InMemoryAuditEventRepository for example simple cache
    @ReadOperation
    InitialAuditEvent getAuditEvents() {
        new InitialAuditEvent(messageId: 'A MessageId I searched for', senderIp: '127.0.0.1', senderName: 'localhost')
    }
}
