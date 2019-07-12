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
}

@Aspect
@Component
class PostFilterAspect {

    @Around('@annotation(PostFilter)')
    Object doFiltering(ProceedingJoinPoint joinPoint) {
        Class returnType = joinPoint.signature.returnType
        isListOfPeople(returnType)
        def result = joinPoint.proceed()
        processResult(result)

    }

    private boolean isListOfPeople(Class<List<Person>> clazz) {
        true
    }

    private boolean isListOfPeople(Object obj) {
        throw new RuntimeException('Invalid Return Type')
    }

    private List<Person> processResult(List<Person> people) {
        people.findAll {
            !it.hidden
        }
    }

    private Object processResult(Object object) {
        throw new RuntimeException('PostFilter applied to method of invalid return type')
    }
}
