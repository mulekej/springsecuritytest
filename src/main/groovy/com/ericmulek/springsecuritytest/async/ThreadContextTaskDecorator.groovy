package com.ericmulek.springsecuritytest.async

import com.ericmulek.springsecuritytest.request.CustomThreadScopedAttributes
import groovy.util.logging.Slf4j
import org.slf4j.MDC
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.task.TaskDecorator
import org.springframework.stereotype.Component

@Slf4j
@Component
class ThreadContextTaskDecorator implements TaskDecorator {

    @Autowired
    CustomThreadScopedAttributes existingAttributes

    ThreadContextTaskDecorator() {
        log.info("Task Decorator being created")
    }

    @Override
    Runnable decorate(Runnable runnable) {
        Map<String, String> contextMap = MDC.getCopyOfContextMap()
        CustomThreadScopedAttributes tempAttributes = existingAttributes.clone()
        [run: {
            try {
                MDC.contextMap = contextMap
                existingAttributes = tempAttributes
                runnable.run()
            } finally {
                MDC.clear()
            }
        }] as Runnable
    }
}
