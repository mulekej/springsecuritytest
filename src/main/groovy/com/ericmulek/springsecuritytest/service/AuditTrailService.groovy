package com.ericmulek.springsecuritytest.service

import com.ericmulek.springsecuritytest.events.AuditEvent
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service

@Service
@Slf4j
class AuditTrailService {
    void sendInitialAudit(AuditEvent o) {
        log.trace('Init Audit')
    }

    void appendToAuditTrail(AuditEvent o) {
        log.trace('Append Audit')
    }
}
