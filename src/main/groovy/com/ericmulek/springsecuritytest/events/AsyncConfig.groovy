package com.ericmulek.springsecuritytest.events


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.ApplicationEventMulticaster
import org.springframework.context.event.SimpleApplicationEventMulticaster
import org.springframework.core.task.SimpleAsyncTaskExecutor

@Configuration
class AsyncConfig{

    @Bean
    ApplicationEventMulticaster applicationEventMulticaster() {
        def asyncTaskExecutor = new SimpleAsyncTaskExecutor('asyncAuditHandler-')
        asyncTaskExecutor.concurrencyLimit = 10 //max 10 requests at a time, creates new thread each time
        new SimpleApplicationEventMulticaster(taskExecutor: asyncTaskExecutor)
        //Alternate config with thead pool
//        new SimpleApplicationEventMulticaster(taskExecutor: new ThreadPoolTaskExecutor())
    }
}
