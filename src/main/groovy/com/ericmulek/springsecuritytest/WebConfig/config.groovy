package com.ericmulek.springsecuritytest.WebConfig

import com.ericmulek.springsecuritytest.domain.Endpoint
import com.ericmulek.springsecuritytest.service.EndpointService
import org.springframework.beans.BeansException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.context.support.SimpleThreadScope

@Configuration
class config {

    @Autowired
    EndpointService endpointService

    @Bean
    @Scope(scopeName = 'thread', proxyMode = ScopedProxyMode.TARGET_CLASS)
    Map threadProperties() {
        new HashMap()
    }

    @Bean
    static BeanFactoryPostProcessor beanFactoryPostProcessor() {
        new BeanFactoryPostProcessor() {
            @Override
            void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
                beanFactory.registerScope("thread", new SimpleThreadScope())
            }
        }
    }

    @Bean
    @Qualifier('thirdParty')
    @Scope(scopeName = 'prototype', proxyMode = ScopedProxyMode.TARGET_CLASS)
    List<Endpoint> thirdPartyEndpoints() {
        endpointService.endpointSet2
    }

    @Bean
    @Qualifier('internal')
    @Scope(scopeName = 'prototype', proxyMode = ScopedProxyMode.TARGET_CLASS)
    List<Endpoint> internalEndpoints() {
        endpointService.endpointSet1
    }
}
