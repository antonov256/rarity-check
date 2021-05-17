package com.atriviss.raritycheck.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class SimpleCorsFilter extends OncePerRequestFilter {
    public SimpleCorsFilter() {
        log.info("SimpleCORSFilter created");
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE, HEAD");
        response.setHeader("Access-Control-Max-Age", "-1");
        response.setHeader("Access-Control-Allow-Headers", "Authorization,Content-Type,Content-Language,Content-Length,Accept,Accept-Language,Accept-Encoding,Host,Connection,X-Requested-With,remember-me");
        response.setHeader("Access-Control-Expose-Headers", "Authorization,Content-Type,Content-Language,Content-Length,Accept,Accept-Language,Accept-Encoding,Host,Connection,X-Requested-With,remember-me");

        chain.doFilter(request, response);
    }
}