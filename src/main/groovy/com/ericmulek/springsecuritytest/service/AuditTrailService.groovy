package com.ericmulek.springsecuritytest.service

import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service

@Service
@Slf4j
class AuditTrailService {
    void sendInitialAudit(Object o) {
        log.trace('Init Audit')
    }

    void appendToAuditTrail(Object o) {
        log.trace('Append Audit')
    }
}
