package com.ericmulek.springsecuritytest.service

import com.ericmulek.springsecuritytest.domain.Endpoint
import org.springframework.stereotype.Service

@Service
class EndpointService {

    List<Endpoint> getThirdPartyEndpoints() {
        [
                new Endpoint(path: 'thirdPartyPath1', token: 'token1', timestamp: System.currentTimeMillis() as String),
                new Endpoint(path: 'thirdPartyPath2', token: 'token2', timestamp: System.currentTimeMillis() as String),
                new Endpoint(path: 'thirdPartyPath3', token: 'token3', timestamp: System.currentTimeMillis() as String)
        ]
    }

    List<Endpoint> getInternalEndpoints() {
        [
                new Endpoint(path: 'internalPath1', token: 'token1'),
                new Endpoint(path: 'internalPath2', token: 'token2'),
                new Endpoint(path: 'internalPath3', token: 'token3')
        ]
    }
}
