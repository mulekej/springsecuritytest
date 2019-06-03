package com.ericmulek.springsecuritytest.events

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler
import org.springframework.stereotype.Component

import java.lang.reflect.Method

//Probably not needed assuming all calls within the Async listeners are hystrix wrapped
@Component
class MyAsyncExceptionHandler implements AsyncUncaughtExceptionHandler{

    @Override
    void handleUncaughtException(Throwable ex, Method method, Object... params) {
        //no-op
    }
}
