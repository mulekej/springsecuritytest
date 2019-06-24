package com.ericmulek.springsecuritytest.service

import com.ericmulek.springsecuritytest.controller.annotations.PostFilter
import com.ericmulek.springsecuritytest.domain.Person
import org.springframework.stereotype.Service

@Service
class AnExternalService {

    @PostFilter('FilterHidden')
    List<Person> getPeople() {
        [
                new Person(firstName: 'Eric', lastName: 'Ericson'),
                new Person(firstName: 'Bob', lastName: 'Bobson', hidden: true)
        ]
    }
}
