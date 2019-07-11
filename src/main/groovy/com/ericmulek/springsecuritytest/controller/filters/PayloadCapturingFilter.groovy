package com.ericmulek.springsecuritytest.controller.filters

import com.ericmulek.springsecuritytest.domain.Person
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
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

    @Autowired
    Person systemUser
    @Autowired
    Map threadProperties

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, java.io.IOException {
        threadProperties.clear()
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request)
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response)
        log.info('Step 0: Set Caching Wrapper on servlet request/response')

        filterChain.doFilter(requestWrapper, responseWrapper)
        log.info("""Step 7: Capture raw contents
                        Inbound Request: ${new String(requestWrapper.contentAsByteArray)}
                        Outbound Resposne: ${responseWrapper.contentInputStream.text}""")
        responseWrapper.copyBodyToResponse()
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.servletPath
        !path.endsWith('/path4')
    }
}
