package com.ericmulek.springsecuritytest.WebConfig

import com.ericmulek.springsecuritytest.domain.Person
import org.springframework.beans.BeansException
import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.context.support.SimpleThreadScope

@Configuration
class config {

    @Bean
    @Scope(scopeName = 'thread', proxyMode = ScopedProxyMode.TARGET_CLASS)
    Person systemUser() {
        new Person()
    }

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
                beanFactory.registerScope("thread", new SimpleThreadScope());
            }
        };
    }


}
