package com.registerapi.register.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ResponseLoggingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(ResponseLoggingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization logic if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        logger.debug("Request: {} {}", request.getProtocol(), request.getRemoteAddr());
        chain.doFilter(request, response);
        logger.debug("Response: {}", response.getContentType());
    }

    @Override
    public void destroy() {
        // Cleanup logic if needed
    }
}


