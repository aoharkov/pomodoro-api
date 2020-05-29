package com.petproject.pomodoro.service;

import com.petproject.pomodoro.config.UserPrincipal;
import com.petproject.pomodoro.entity.User;
import com.petproject.pomodoro.exceptions.NoSuchElementException;
import com.petproject.pomodoro.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {

    private static final String NICKNAME = "Nick";
    private final User user = User.builder()
                                  .id(1L)
                                  .nickname("admin")
                                  .password("PASSWORD")
                                  .pomodoroList(null)
                                  .build();

    private UserPrincipal userPrincipal = new UserPrincipal(user);

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsername_returns_UserDetails_with_embeddedUser() {
        when(userRepository.findByNickname(NICKNAME)).thenReturn(Optional.of(user));

        UserPrincipal actual = (UserPrincipal) userDetailsService.loadUserByUsername(NICKNAME);
        UserPrincipal expected = userPrincipal;

        assertEquals(expected, actual);
    }

    @Test
    void loadUserByUsername_throws_NoSuchElementException_on_nonExistedUser() {
        when(userRepository.findByNickname(NICKNAME)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> userDetailsService.loadUserByUsername(NICKNAME));
    }
}