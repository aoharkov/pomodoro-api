package com.petproject.pomodoro.service;

import com.petproject.pomodoro.entity.User;
import com.petproject.pomodoro.exceptions.BadRequestException;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    public static final long USER3_ID = 3L;
    public static final String USER3_NICKNAME = "user";
    private final User user3 = new User(USER3_ID, USER3_NICKNAME, "password", null);
    private final User user1 = new User(1L, "admin", "password", null);
    private final User user2 = new User(2L, "artem", "password", null);
    private final List<User> listOfTwoUsers = Arrays.asList(user1, user2);

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @AfterEach
    public void resetMocks() {
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
    void updateShouldThrowBadRequestException() {
        assertThrows(BadRequestException.class, () -> userService.update(user3, 1L));
    }

    @Test
    void updateAllShouldSaveTwice() {
        when(userRepository.save(user1)).thenReturn(user1);
        when(userRepository.save(user2)).thenReturn(user2);

        userService.updateAll(listOfTwoUsers);

        verify(userRepository, times(2)).save(any());
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