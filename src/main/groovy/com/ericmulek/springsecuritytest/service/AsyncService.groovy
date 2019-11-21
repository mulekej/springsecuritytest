package com.ericmulek.springsecuritytest.service

import com.ericmulek.springsecuritytest.request.CustomThreadScopedAttributes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.AsyncResult
import org.springframework.stereotype.Service

import java.util.concurrent.CompletableFuture
import java.util.concurrent.Future
import java.util.function.Supplier

@Service
class AsyncService {

    @Autowired
    CustomThreadScopedAttributes attributes
//
//    @Autowired
//    ExecutorService executorService

    AsyncService() {
//        def queue = new ArrayBlockingQueue(2)
//        this.executorService = new ThreadPoolExecutor(2, 2, 2, TimeUnit.MINUTES, queue)
    }

    @SuppressWarnings('UnnecessaryPublicModifier')
    <T> CompletableFuture<T> process(Supplier<T> supplier) {
        def tempAttributes = attributes.clone() as CustomThreadScopedAttributes
        CompletableFuture.supplyAsync {
            attributes = tempAttributes
            supplier.get()
        }
    }

    @Async('asyncThreadPool')
    Future<String> processWithSpring() {
        sleep(10000)
        new AsyncResult<String>('Hello from Async World')
    }
}
