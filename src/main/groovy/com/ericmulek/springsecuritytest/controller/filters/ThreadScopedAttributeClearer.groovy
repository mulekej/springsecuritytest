package com.ericmulek.springsecuritytest.controller.filters

import com.ericmulek.springsecuritytest.request.CustomThreadScopedAttributes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class ThreadScopedAttributeClearer extends OncePerRequestFilter {

    @Autowired
    CustomThreadScopedAttributes attributes

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        attributes.clear()
        filterChain.doFilter(request, response)
    }
}
