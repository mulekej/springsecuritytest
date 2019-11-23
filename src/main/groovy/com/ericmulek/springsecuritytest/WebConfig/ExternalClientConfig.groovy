package com.ericmulek.springsecuritytest.WebConfig

import com.ericmulek.springsecuritytest.domain.Endpoint
import com.ericmulek.springsecuritytest.service.EndpointService
import com.ericmulek.springsecuritytest.service.ExternalClient
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.core.convert.ConversionService
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Slf4j
@Configuration
class ExternalClientConfig {

    @Autowired
    ConversionService conversionService
    @Autowired
    EndpointService endpointService

    @Bean
    @Qualifier('externalSet1')
    @Scope(scopeName = 'prototype', proxyMode = ScopedProxyMode.TARGET_CLASS)
    List<ExternalClient> getSetOneClients() {
        log.info('creating new beans')
        endpointService.endpointSet1.collect { Endpoint endpoint ->
            this.conversionService.convert(endpoint, ExternalClient).tap {
                it.description = 'Set1'
            }
        }
    }

    @Bean
    @Qualifier('externalSet2')
    @Scope(scopeName = 'prototype', proxyMode = ScopedProxyMode.TARGET_CLASS)
    List<ExternalClient> getSetTwoClients() {
        endpointService.endpointSet2.collect { Endpoint endpoint ->
            this.conversionService.convert(endpoint, ExternalClient).tap {
                it.description = 'Set1'
            }
        }
    }
}

@Component
class EndpointToExternalClientConverter implements Converter<Endpoint, ExternalClient> {

    @Override
    ExternalClient convert(Endpoint endpoint) {
        new ExternalClient(
                endpoint: endpoint
        )
    }
}
