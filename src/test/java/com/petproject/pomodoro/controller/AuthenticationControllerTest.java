package com.petproject.pomodoro.controller;

import com.petproject.pomodoro.config.jwt.JwtProcessor;
import com.petproject.pomodoro.web.dto.JwtRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationControllerTest {
    private static final String TOKEN_STUB = "token-stub";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "password";

    @InjectMocks
    private AuthenticationController instance;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtProcessor jwtProcessor;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private UsernamePasswordAuthenticationToken authenticationToken;

    @Mock
    private UserDetails userDetails;

    @Mock
    private JwtRequestDto jwtRequestDto;

    @Test
    void authenticate() {
        when(jwtRequestDto.getUsername()).thenReturn(USERNAME);
        when(jwtRequestDto.getPassword()).thenReturn(PASSWORD);
        when(authenticationManager.authenticate(any())).thenReturn(authenticationToken);
        when(userDetailsService.loadUserByUsername(anyString())).thenReturn(userDetails);
        when(userDetails.getAuthorities()).thenReturn(Collections.emptyList());
        when(jwtProcessor.createJwt(USERNAME, Collections.emptyList())).thenReturn(TOKEN_STUB);

        assertEquals(TOKEN_STUB, instance.authenticate(jwtRequestDto).getBody().getToken());
    }
}
