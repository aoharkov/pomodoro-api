package com.petproject.pomodoro.config.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class JwtFilter extends GenericFilterBean {

    private JwtProcessor jwtProcessor;

    JwtFilter(JwtProcessor jwtProcessor) {
        this.jwtProcessor = jwtProcessor;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        String jwt = jwtProcessor.getJwt((HttpServletRequest) request);
        if (tryToValidate(jwt)) {
            Authentication authentication = jwtProcessor.getAuthentication(jwt);

            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean tryToValidate(String jwt) {
        boolean isValid = false;
        try {
            isValid = jwtProcessor.isValid(jwt);
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT: {}", jwt);
        }
        return isValid;
    }

}
