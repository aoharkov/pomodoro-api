package com.petproject.pomodoro.service;

import com.petproject.pomodoro.entity.Pomodoro;
import com.petproject.pomodoro.exceptions.BadRequestException;
import com.petproject.pomodoro.repository.PomodoroRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PomodoroServiceImplTest {
    public static final long POM_ID = 3L;
    public static final long USER_ID = 1L;
    private final Pomodoro pom1 = new Pomodoro(POM_ID, USER_ID, "test", 25,
            LocalDateTime.of(2019, 5, 20, 9, 30, 20));
    private final Pomodoro pom2 = new Pomodoro(4L, USER_ID, "test2", 25,
            LocalDateTime.of(2019, 5, 20, 10, 0, 20));
    private final List<Pomodoro> pomodoroListOfSizeTwo = Arrays.asList(pom1, pom2);

    @Mock
    private PomodoroRepository pomodoroRepository;

    @InjectMocks
    private PomodoroServiceImpl pomodoroService;

    @AfterEach
    public void resetMocks() {
        Mockito.reset(pomodoroRepository);
    }

    @Test
    void saveShouldReturnId() {
        when(pomodoroRepository.save(pom1)).thenReturn(pom1);

        Long actual = pomodoroService.save(pom1, USER_ID);

        assertEquals(POM_ID, actual);
        verify(pomodoroRepository).save(pom1);
    }

    @Test
    void saveShouldThrowBadRequestException() {
        assertThrows(BadRequestException.class, () -> pomodoroService.save(pom1, USER_ID + 1));
    }

    @Test
    void findByUserIdShouldReturnListOfPomodoros() {
        when(pomodoroRepository.findByUserId(USER_ID)).thenReturn(pomodoroListOfSizeTwo);

        List<Pomodoro> actual = pomodoroService.findByUserId(USER_ID);

        assertEquals(pomodoroListOfSizeTwo, actual);
        verify(pomodoroRepository).findByUserId(USER_ID);
    }

    @Test
    void updateShouldSavePomodoro() {
        when(pomodoroRepository.save(pom1)).thenReturn(pom1);

        pomodoroService.update(pom1, POM_ID, USER_ID);

        verify(pomodoroRepository).save(pom1);
    }

    @Test
    void updateShouldThrowBadRequestExceptionBecauseIdIsDifferent() {
        assertThrows(BadRequestException.class, () -> pomodoroService.update(pom1, POM_ID + 1, USER_ID));
    }

    @Test
    void updateShouldThrowBadRequestExceptionBecauseUserIdIsDifferent() {
        assertThrows(BadRequestException.class, () -> pomodoroService.update(pom1, POM_ID, USER_ID + 1));
    }

    @Test
    void deleteShouldCallDelete() {
        when(pomodoroRepository.findById(POM_ID)).thenReturn(Optional.of(pom1));

        pomodoroService.delete(POM_ID, USER_ID);

        verify(pomodoroRepository).findById(POM_ID);
        verify(pomodoroRepository).delete(pom1);
    }

    @Test
    void deleteShouldNotCallDelete() {
        when(pomodoroRepository.findById(POM_ID)).thenReturn(Optional.empty());

        pomodoroService.delete(POM_ID, USER_ID);

        verify(pomodoroRepository).findById(POM_ID);
        verify(pomodoroRepository, times(0)).delete(any());
    }

    @Test
    void deleteShouldThrowBadRequestException() {
        when(pomodoroRepository.findById(POM_ID)).thenReturn(Optional.of(pom1));

        assertThrows(BadRequestException.class, () -> pomodoroService.delete(POM_ID, USER_ID + 1));
        verify(pomodoroRepository).findById(POM_ID);
    }
}