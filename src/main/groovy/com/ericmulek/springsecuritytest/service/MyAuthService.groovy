package com.ericmulek.springsecuritytest.service

import org.springframework.stereotype.Service

@Service
class MyAuthService {

    boolean authenticateCert(String subject, String issuer) {
        true
    }
}
