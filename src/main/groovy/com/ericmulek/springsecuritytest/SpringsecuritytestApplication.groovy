package com.ericmulek.springsecuritytest

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync
class SpringsecuritytestApplication {

    static void main(String[] args) {
        SpringApplication.run(SpringsecuritytestApplication, args)
    }

}
