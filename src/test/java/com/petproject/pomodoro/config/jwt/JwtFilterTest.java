package com.petproject.pomodoro.config.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JwtFilterTest {

    private static String TOKEN_STUB = "Bearer token-stub";

    @InjectMocks
    private JwtFilter instance;

    @Mock
    private JwtProcessor jwtProcessor;

    private ServletRequest servletRequest = new HttpServletRequestStub() {
        @Override
        public String getHeader(String name) {
            return TOKEN_STUB;
        }
    };

    @Mock
    private ServletResponse servletResponse;

    @Mock
    private Authentication authentication;

    @Mock
    private FilterChain filterChain;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        when(jwtProcessor.getJwt((HttpServletRequest) servletRequest)).thenReturn(TOKEN_STUB);
    }

    @Test
    void doFilterCorrectly_WhenValidJWT() throws IOException, ServletException {
        when(jwtProcessor.isValid(TOKEN_STUB)).thenReturn(true);
        when(jwtProcessor.getAuthentication(TOKEN_STUB)).thenReturn(authentication);

        instance.doFilter(servletRequest, servletResponse, filterChain);

        verify(jwtProcessor).getJwt((HttpServletRequest) servletRequest);
        verify(jwtProcessor).getAuthentication(TOKEN_STUB);
        verify(filterChain).doFilter(servletRequest, servletResponse);
    }

    @Test
    void doFilter_notAuthenticate_on_expiredJwt() throws IOException, ServletException {
        when(jwtProcessor.isValid(TOKEN_STUB)).thenThrow(ExpiredJwtException.class);

        instance.doFilter(servletRequest, servletResponse, filterChain);

        verify(jwtProcessor).isValid(TOKEN_STUB);
        verify(jwtProcessor, never()).getAuthentication(TOKEN_STUB);
    }
}