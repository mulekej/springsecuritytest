package com.ericmulek.springsecuritytest.events


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

@Configuration
class AsyncConfig{

//    @Bean
//    ApplicationEventMulticaster applicationEventMulticaster(@Value('Notification.processor.threadpool.size') int threadPoolSize) {
//        def asyncTaskExecutor = new SimpleAsyncTaskExecutor('asyncAuditHandler-')
//        asyncTaskExecutor.concurrencyLimit = 10 //max 10 requests at a time, creates new thread each time
////        new SimpleApplicationEventMulticaster(taskExecutor: asyncTaskExecutor)
//        //Alternate config with thead pool
//        new SimpleApplicationEventMulticaster(taskExecutor: new ThreadPoolTaskExecutor(corePoolSize: threadPoolSize))
//    }

    @Bean
    ThreadPoolTaskExecutor asyncThreadPool() {
        new ThreadPoolTaskExecutor(corePoolSize:  5)
    }
}
