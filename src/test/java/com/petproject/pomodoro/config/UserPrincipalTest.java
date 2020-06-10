package com.petproject.pomodoro.config;

import com.petproject.pomodoro.entity.Role;
import com.petproject.pomodoro.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserPrincipalTest {
    public static final String ROLE = "USER";
    private final User user = User.builder()
            .role(Role.valueOf(ROLE))
            .build();

    @Test
    void getAuthoritiesShouldReturnCollectionWithSingleRole() {
        Integer expectedSize = 1;
        GrantedAuthority expectedElement = new SimpleGrantedAuthority(ROLE);
        UserPrincipal userPrincipal = new UserPrincipal(user);

        Collection<? extends GrantedAuthority> actual = userPrincipal.getAuthorities();

        assertTrue(actual.contains(expectedElement));
        assertEquals(expectedSize, actual.size());
    }
}
