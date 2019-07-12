package com.ericmulek.springsecuritytest.service

import com.ericmulek.springsecuritytest.controller.annotations.PostFilter
import com.ericmulek.springsecuritytest.domain.Endpoint
import com.ericmulek.springsecuritytest.domain.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Service

@Service
class AnExternalService {

    @Autowired
    List<Endpoint> internalEndpoints

    @Autowired
    List<Endpoint> thirdPartyEndpoints

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
}
