package com.petproject.pomodoro.controller;

import com.petproject.pomodoro.config.jwt.JwtProcessor;
import com.petproject.pomodoro.web.dto.JwtRequestDto;
import com.petproject.pomodoro.web.dto.JwtResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtProcessor jwtProcessor;
    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JwtProcessor jwtProcessor,
                                    @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtProcessor = jwtProcessor;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity authenticate(@RequestBody JwtRequestDto requestDto) {
        String username = requestDto.getUsername();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        
        String token = jwtProcessor.createJwt(username,
                                              (Collection<GrantedAuthority>) userDetails.getAuthorities());

        return ResponseEntity.ok(new JwtResponseDto(token));
    }
}
