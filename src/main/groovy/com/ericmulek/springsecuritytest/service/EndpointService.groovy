package com.ericmulek.springsecuritytest.service

import com.ericmulek.springsecuritytest.domain.Endpoint
import org.springframework.stereotype.Service

@Service
class EndpointService {

    List<Endpoint> getEndpointSet2() {
        [
                new Endpoint(path: 'thirdPartyPath1', token: 'token1', timestamp: System.currentTimeMillis() as String),
                new Endpoint(path: 'thirdPartyPath2', token: 'token2', timestamp: System.currentTimeMillis() as String),
                new Endpoint(path: 'thirdPartyPath3', token: 'token3', timestamp: System.currentTimeMillis() as String)
        ]
    }

    List<Endpoint> getEndpointSet1() {
        [
                new Endpoint(path: 'internalPath1', token: 'token1', states: ['MN']),
                new Endpoint(path: 'internalPath2', token: 'token2', states: ['WA']),
                new Endpoint(path: 'internalPath3', token: 'token3', states: ['OR'])
        ]
    }
}
