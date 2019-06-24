package com.ericmulek.springsecuritytest.controller.annotations

import com.ericmulek.springsecuritytest.domain.Person
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface PostFilter {
    String value() default ''
}

@Aspect
@Component
class PostFilterAspect {

    @Around('@annotation(PostFilter)')
    List<Person> doFiltering(ProceedingJoinPoint joinPoint) {
        List<Person> result = joinPoint.proceed() as List<Person>
        result.findAll {
            !it.hidden
        }
    }
}
