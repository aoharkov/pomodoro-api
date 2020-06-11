package com.petproject.pomodoro.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter

@Configuration("jwtProperties")
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String secret;
    private long expirationSeconds;
}
