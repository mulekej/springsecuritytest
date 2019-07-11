package com.ericmulek.springsecuritytest.WebConfig

import org.springframework.beans.BeansException
import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.context.support.SimpleThreadScope

class SimpleThreadScopeRegister implements BeanFactoryPostProcessor{
    @Override
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        beanFactory.registerScope(SimpleThreadScope.simpleName, new SimpleThreadScope())
    }
}
