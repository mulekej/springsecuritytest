package com.ericmulek.springsecuritytest.async

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.AsyncConfigurer
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

import java.util.concurrent.Executor

@Configuration
@EnableAsync
class AsyncConfig implements AsyncConfigurer {

    @Autowired
    ThreadContextTaskDecorator threadContextTaskDecorator

    @Override
    Executor getAsyncExecutor() {
        new ThreadPoolTaskExecutor(corePoolSize: 5, taskDecorator:threadContextTaskDecorator).tap {
            it.initialize()
        }
    }

    @Bean
    ThreadPoolTaskExecutor asyncThreadPool() {
        new ThreadPoolTaskExecutor(corePoolSize: 5,taskDecorator:threadContextTaskDecorator)
    }
}
