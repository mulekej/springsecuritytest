package com.ericmulek.springsecuritytest.request

import groovy.util.logging.Slf4j
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component

import java.time.Instant

@Component
@Scope(scopeName = 'thread', proxyMode = ScopedProxyMode.TARGET_CLASS)
@Slf4j
class CustomThreadScopedAttributes {

    String messageId
    String traceId
    String threadName
    Instant receivedTimeStamp

    void clear() {
        properties.each { String key, Object val ->
            if (!['metaClass', 'class'].contains(key)) {
                this[key] = null
            }
        }
    }
}
