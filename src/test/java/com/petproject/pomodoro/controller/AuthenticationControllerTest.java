package com.petproject.pomodoro.controller;

import com.petproject.pomodoro.config.jwt.JwtProcessor;
import com.petproject.pomodoro.entity.Role;
import com.petproject.pomodoro.web.dto.JwtRequestDto;
import com.petproject.pomodoro.web.dto.JwtResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationControllerTest {

    private String TOKEN_STUB = "token-stub";
    private String USERNAME = "user";

    @InjectMocks
    private AuthenticationController instance;

    @Mock
    private JwtProcessor jwtProcessor;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private UsernamePasswordAuthenticationToken authenticationToken;

    @Mock
    private UserDetails userDetails;

    private JwtRequestDto jwtRequestDto = new JwtRequestDto(USERNAME, USERNAME);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void authenticate() {
        when(authenticationManager.authenticate(authenticationToken)).thenReturn(authenticationToken);
        ArrayList authorities = new ArrayList<Role>();
        authorities.add(Role.USER);

        when(userDetails.getAuthorities()).thenReturn(authorities);
        when(userDetailsService.loadUserByUsername(anyString())).thenReturn(userDetails);

        when(jwtProcessor.createJwt(USERNAME, authorities)).thenReturn(TOKEN_STUB);

        assertEquals(instance.authenticate(jwtRequestDto), ResponseEntity.ok(new JwtResponseDto(TOKEN_STUB)));
    }
}