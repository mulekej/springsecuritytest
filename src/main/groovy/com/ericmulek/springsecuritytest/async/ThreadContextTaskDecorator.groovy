package com.ericmulek.springsecuritytest.async

import com.ericmulek.springsecuritytest.request.CustomThreadScopedAttributes
import org.slf4j.MDC
import org.springframework.core.task.TaskDecorator

class ThreadContextTaskDecorator implements TaskDecorator {

    CustomThreadScopedAttributes existingAttributes

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
