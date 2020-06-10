package com.petproject.pomodoro.controller;

import com.petproject.pomodoro.entity.Pomodoro;
import com.petproject.pomodoro.entity.User;
import com.petproject.pomodoro.service.PomodoroService;
import com.petproject.pomodoro.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    private static final long USER_ID = 1L;
    private static final long POM_ID = 3L;
    private static final String USER_NICKNAME = "user";
    private static final String PASSWORD = "password";

    private static final LocalDateTime time1 =
            LocalDateTime.of(2019, 5, 20, 9, 30, 20);

    private static final LocalDateTime time2 =
            LocalDateTime.of(2019, 5, 20, 10, 0, 20);

    private final User user = User.builder()
                                  .id(USER_ID)
                                  .nickname(USER_NICKNAME)
                                  .password(PASSWORD)
                                  .build();

    private final User user2 = User.builder()
                                   .id(2L)
                                   .nickname("artem")
                                   .password(PASSWORD)
                                   .build();

    private final Pomodoro pomodoro = Pomodoro.builder()
                                              .id(POM_ID)
                                              .user(user)
                                              .description("test")
                                              .build();

    private final Pomodoro pomodoro2 = Pomodoro.builder()
                                               .id(4L)
                                               .user(user)
                                               .description("test2")
                                               .build();

    private final List<User> listOfTwoUsers = Arrays.asList(user, user2);
    private final List<Pomodoro> listOfTwoPomodoros = Arrays.asList(pomodoro, pomodoro2);

    @Mock
    private UserService userService;

    @Mock
    private PomodoroService pomodoroService;

    @InjectMocks
    private UserController userController;

    @AfterEach
    void resetMocks() {
        Mockito.reset(userService, pomodoroService);
    }

    @Test
    void createNewUserShouldSaveUser() {
        when(userService.save(user)).thenReturn(USER_ID);

        Long actual = userController.createNewUser(user).getBody();

        assertEquals(USER_ID, actual);
        verify(userService).save(user);
    }

    @Test
    void createNewPomodoroShouldSavePomodoro() {
        when(pomodoroService.save(pomodoro)).thenReturn(POM_ID);

        Long actual = userController.createNewPomodoro(USER_ID, pomodoro).getBody();

        assertEquals(POM_ID, actual);
        verify(pomodoroService).save(pomodoro);
    }

    @Test
    void readAllUsersShouldReturnAllUsers() {
        when(userService.findAll()).thenReturn(listOfTwoUsers);

        List<User> actual = userController.readAllUsers().getBody();

        assertArrayEquals(listOfTwoUsers.toArray(), actual.toArray());
        verify(userService).findAll();
    }

    @Test
    void readUserByIdShouldReturnUserById() {
        when(userService.findById(USER_ID)).thenReturn(user);

        User actual = userController.readUserById(USER_ID).getBody();

        assertEquals(user, actual);
        verify(userService).findById(USER_ID);
    }

    @Test
    void readAllPomodorosShouldReturnAllPomodorosOfUser() {
        when(pomodoroService.findByUserId(USER_ID)).thenReturn(listOfTwoPomodoros);

        List<Pomodoro> actual = userController.readAllPomodoros(USER_ID).getBody();

        assertArrayEquals(listOfTwoPomodoros.toArray(), actual.toArray());
        verify(pomodoroService).findByUserId(USER_ID);
    }

    @Test
    void updateUserByIdShouldUser() {
        doNothing().when(userService).update(user);

        userController.updateUserById(USER_ID, user);

        verify(userService).update(user);
    }

    @Test
    void updatePomodoroForUserWithIdShouldUpdatePomodoro() {
        doNothing().when(pomodoroService).update(pomodoro);

        userController.updatePomodoro(pomodoro);

        verify(pomodoroService).update(pomodoro);
    }

    @Test
    void deleteUserByIdShouldDeleteUser() {
        doNothing().when(userService).delete(USER_ID);

        userController.deleteUserById(USER_ID);

        verify(userService).delete(USER_ID);
    }

    @Test
    void deletePomodoroForUserWithIdShouldDeletePomodoro() {
        doNothing().when(pomodoroService).delete(POM_ID, USER_ID);

        userController.deletePomodoroForUserWithId(USER_ID, POM_ID);

        verify(pomodoroService).delete(POM_ID, USER_ID);
    }
}
