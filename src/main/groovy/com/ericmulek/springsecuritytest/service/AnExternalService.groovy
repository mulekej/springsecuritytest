package com.ericmulek.springsecuritytest.service

import com.ericmulek.springsecuritytest.controller.annotations.PostFilter
import com.ericmulek.springsecuritytest.domain.Endpoint
import com.ericmulek.springsecuritytest.domain.Person
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Service

@Service
@Slf4j
class AnExternalService {

    @Autowired
    List<Endpoint> internalEndpoints

    @Autowired
    List<Endpoint> thirdPartyEndpoints

    @Autowired
    @Qualifier('externalSet1')
    List<ExternalClient> clients

    @Autowired
    ApplicationContext context

    @PostFilter
    List<Person> getPeople() {
        [
                new Person(firstName: 'Eric', lastName: 'Ericson'),
                new Person(firstName: 'Bob', lastName: 'Bobson', hidden: true)
        ]
    }

    List getThingsFromOtherServices() {
        thirdPartyEndpoints*.timestamp
//        context.getBean()
//        List<Endpoint> vals = context.
//        [vals[0].timestamp]
    }

    void process() {
        log.info('before find all')
        def stateEndpointClients = clients.findAll {
            log.info('in findall')
            'MN' in it.endpoint.states
        }
        log.info('after findAll')
        stateEndpointClients*.process()
        log.info('afterProcess')
    }
}
