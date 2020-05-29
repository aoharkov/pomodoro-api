package com.petproject.pomodoro.service;

import com.petproject.pomodoro.entity.User;
import com.petproject.pomodoro.exceptions.NoSuchElementException;
import com.petproject.pomodoro.exceptions.SuchElementAlreadyExistsException;
import com.petproject.pomodoro.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private static final long USER3_ID = 3L;
    private static final String USER3_NICKNAME = "user";
    private static final String PASSWORD = "password";

    private final User user1 = User.builder()
                                   .id(1L)
                                   .nickname("admin")
                                   .password(PASSWORD)
                                   .pomodoroList(null)
                                   .build();

    private final User user2 = User.builder()
                                   .id(2L)
                                   .nickname("artem")
                                   .password(PASSWORD)
                                   .pomodoroList(null)
                                   .build();

    private final User user3 = User.builder()
                                   .id(USER3_ID)
                                   .nickname(USER3_NICKNAME)
                                   .password(PASSWORD)
                                   .pomodoroList(null)
                                   .build();

    private final List<User> listOfTwoUsers = Arrays.asList(user1, user2);

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @AfterEach
    void resetMocks() {
        Mockito.reset(userRepository);
    }

    @Test
    void saveShouldReturnIdEqualTo3() {
        when(userRepository.save(user3)).thenReturn(user3);
        when(userRepository.findByNickname(USER3_NICKNAME)).thenReturn(Optional.empty());

        Long actual = userService.save(user3);

        assertEquals(USER3_ID, actual);
        verify(userRepository).save(user3);
        verify(userRepository).findByNickname(USER3_NICKNAME);
    }

    @Test
    void saveShouldThrowSuchElementAlreadyExistsException() {
        when(userRepository.findByNickname(USER3_NICKNAME)).thenReturn(Optional.of(user3));

        assertThrows(SuchElementAlreadyExistsException.class, () -> userService.save(user3));
        verify(userRepository).findByNickname(USER3_NICKNAME);
    }

    @Test
    void findByIdShouldReturnUser() {
        when(userRepository.findById(USER3_ID)).thenReturn(Optional.of(user3));

        User actual = userService.findById(USER3_ID);

        assertEquals(user3, actual);
        verify(userRepository).findById(USER3_ID);
    }

    @Test
    void findByIdShouldThrowNoSuchElementException() {
        when(userRepository.findById(USER3_ID)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> userService.findById(USER3_ID));
        verify(userRepository).findById(USER3_ID);
    }

    @Test
    void findAllShouldReturnListOfTwoElements() {
        when(userRepository.findAll()).thenReturn(listOfTwoUsers);

        List<User> actual = userService.findAll();

        assertArrayEquals(listOfTwoUsers.toArray(), actual.toArray());
        verify(userRepository).findAll();
    }

    @Test
    void updateShouldSaveUser() {
        when(userRepository.save(user3)).thenReturn(user3);

        userService.update(user3);

        verify(userRepository).save(user3);
    }

    @Test
    void deleteShouldCallDelete() {
        when(userRepository.findById(USER3_ID)).thenReturn(Optional.of(user3));

        userService.delete(USER3_ID);

        verify(userRepository).findById(USER3_ID);
        verify(userRepository).delete(user3);
    }

    @Test
    void deleteShouldNotCallDelete() {
        when(userRepository.findById(USER3_ID)).thenReturn(Optional.empty());

        userService.delete(USER3_ID);

        verify(userRepository).findById(USER3_ID);
        verify(userRepository, times(0)).delete(user3);
    }
}