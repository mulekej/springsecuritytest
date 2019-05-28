package com.ericmulek.springsecuritytest.controller.filters

import groovy.util.logging.Slf4j
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@Slf4j
class PayloadCapturingFilter extends OncePerRequestFilter{
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, java.io.IOException {

        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request)
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response)

        filterChain.doFilter(requestWrapper, responseWrapper)
        log.info("******************** ${responseWrapper.contentInputStream.text}")
        log.info("******************** ${new String(requestWrapper.contentAsByteArray)}")
        responseWrapper.copyBodyToResponse()
    }

}
