package com.ericmulek.springsecuritytest.request

import spock.lang.Specification

class CustomThreadScopedAttributesTest extends Specification {

    CustomThreadScopedAttributes classUnderTest

    void setup() {
        classUnderTest = new CustomThreadScopedAttributes(traceId: 'aTestValue')
    }

    def 'Should clear any properties already set'() {
        when:
        classUnderTest.clear()

        then:
        classUnderTest.traceId == null
    }
}
