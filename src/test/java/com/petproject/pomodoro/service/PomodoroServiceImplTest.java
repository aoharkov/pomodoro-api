package com.petproject.pomodoro.service;

import com.petproject.pomodoro.entity.Pomodoro;
import com.petproject.pomodoro.entity.User;
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
    private static final long POM_ID = 3L;
    private static final long USER_ID = 1L;

    private static final LocalDateTime time1 =
            LocalDateTime.of(2019, 5, 20, 9, 30, 20);

    private static final LocalDateTime time2 =
            LocalDateTime.of(2019, 5, 20, 10, 0, 20);

    private final User user = User.builder()
                                  .id(USER_ID)
                                  .build();

    private final Pomodoro pomodoro1 = Pomodoro.builder()
                                               .id(POM_ID)
                                               .user(user)
                                               .description("test")
                                               .build();

    private final Pomodoro pomodoro2 = Pomodoro.builder()
                                               .id(4L)
                                               .user(user)
                                               .description("test2")
                                               .build();

    private final List<Pomodoro> pomodoroListOfSizeTwo = Arrays.asList(pomodoro1, pomodoro2);

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
        when(pomodoroRepository.save(pomodoro1)).thenReturn(pomodoro1);

        Long actual = pomodoroService.save(pomodoro1);

        assertEquals(POM_ID, actual);
        verify(pomodoroRepository).save(pomodoro1);
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
        when(pomodoroRepository.save(pomodoro1)).thenReturn(pomodoro1);

        pomodoroService.update(pomodoro1);

        verify(pomodoroRepository).save(pomodoro1);
    }

    @Test
    void deleteShouldCallDelete() {
        when(pomodoroRepository.findById(POM_ID)).thenReturn(Optional.of(pomodoro1));

        pomodoroService.delete(POM_ID, USER_ID);

        verify(pomodoroRepository).findById(POM_ID);
        verify(pomodoroRepository).delete(pomodoro1);
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
        when(pomodoroRepository.findById(POM_ID)).thenReturn(Optional.of(pomodoro1));

        assertThrows(BadRequestException.class, () -> pomodoroService.delete(POM_ID, USER_ID + 1));
        verify(pomodoroRepository).findById(POM_ID);
    }
}
