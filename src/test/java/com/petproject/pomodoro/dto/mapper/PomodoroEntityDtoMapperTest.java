package com.petproject.pomodoro.dto.mapper;

import com.petproject.pomodoro.dto.PomodoroDto;
import com.petproject.pomodoro.entity.*;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Student on 15.06.2020
 */
class PomodoroEntityDtoMapperTest {
    private static final PomodoroEntityDtoMapper pomodoroEntityDtoMapper = Mappers
            .getMapper(PomodoroEntityDtoMapper.class);
    private static final ZonedDateTime zonedDateTime = ZonedDateTime.of(2019, 5, 20, 9,
            30, 20, 0, ZoneId.of("UTC"));
    private static final Long ID = 1L;
    private static final String SPHERE_TITLE = "Sphere Title";
    private static final String USER_EMAIL = "pomodoro@gmail.com";
    private static final String USER_NICKNAME = "pomodoro_user";
    private static final String USER_PASSWORD = "qwerty12345";
    private static final Role USER_ROLE = Role.USER;
    private static final int LENGTH = 25;
    private static final String CATEGORY_TITLE = "Category Title";
    private static final Status POMODORO_STATUS = Status.FINISHED;
    private static final String POMODORO_DESCRIPTION = "Pomodoro Description";

    private final Sphere sphere = Sphere.builder()
            .id(ID)
            .title(SPHERE_TITLE)
            .build();

    private final User user = User.builder()
            .id(ID)
            .email(USER_EMAIL)
            .nickname(USER_NICKNAME)
            .password(USER_PASSWORD)
            .role(USER_ROLE)
            .registrationDate(zonedDateTime)
            .categories(null)
            .storage(null)
            .defaultLenght(LENGTH)
            .build();

    private final Category category = Category.builder()
            .id(ID)
            .user(user)
            .sphere(sphere)
            .title(CATEGORY_TITLE)
            .build();

    private final Pomodoro testEntity = Pomodoro.builder()
            .id(ID)
            .user(user)
            .category(category)
            .description(POMODORO_DESCRIPTION)
            .declaredLength(LENGTH)
            .trackedLength(LENGTH)
            .status(POMODORO_STATUS)
            .startedDateTime(zonedDateTime)
            .lastStart(zonedDateTime)
            .finishedDateTime(zonedDateTime)
            .build();

    private final PomodoroDto testDto = PomodoroDto.builder()
            .userEmail(USER_EMAIL)
            .sphere(SPHERE_TITLE)
            .category(CATEGORY_TITLE)
            .description(POMODORO_DESCRIPTION)
            .declaredLength(LENGTH)
            .trackedLength(LENGTH)
            .status(POMODORO_STATUS.name())
            .startedDateTime(zonedDateTime.toString())
            .lastStart(zonedDateTime.toString())
            .finishedDateTime(zonedDateTime.toString())
            .build();

    @Test
    void afterMappingEntityToDtoFieldsMustBeEquals() {
        PomodoroDto pomodoroDto = pomodoroEntityDtoMapper.mapEntityToDto(testEntity);
        assertAll(
                () -> assertEquals(testEntity.getUser().getEmail(), pomodoroDto.getUserEmail()),
                () -> assertEquals(testEntity.getCategory().getSphere().getTitle(), pomodoroDto.getSphere()),
                () -> assertEquals(testEntity.getCategory().getTitle(), pomodoroDto.getCategory()),
                () -> assertEquals(testEntity.getDescription(), pomodoroDto.getDescription()),
                () -> assertEquals(testEntity.getDeclaredLength(), pomodoroDto.getDeclaredLength()),
                () -> assertEquals(testEntity.getTrackedLength(), pomodoroDto.getTrackedLength()),
                () -> assertEquals(testEntity.getStatus().name(), pomodoroDto.getStatus()),
                () -> assertEquals(testEntity.getStartedDateTime().toString(), pomodoroDto.getStartedDateTime()),
                () -> assertEquals(testEntity.getLastStart().toString(), pomodoroDto.getLastStart()),
                () -> assertEquals(testEntity.getFinishedDateTime().toString(), pomodoroDto.getFinishedDateTime())
        );
    }

    @Test
    void afterMappingDtoToEntityFieldsMustBeEquals() {
        Pomodoro pomodoro = pomodoroEntityDtoMapper.mapDtoToEntity(testDto);
        assertAll(
                () -> assertEquals(testDto.getUserEmail(), pomodoro.getUser().getEmail()),
                () -> assertEquals(testDto.getSphere(), pomodoro.getCategory().getSphere().getTitle()),
                () -> assertEquals(testDto.getCategory(), pomodoro.getCategory().getTitle()),
                () -> assertEquals(testDto.getDescription(), pomodoro.getDescription()),
                () -> assertEquals(testDto.getDeclaredLength(), pomodoro.getDeclaredLength()),
                () -> assertEquals(testDto.getTrackedLength(), pomodoro.getTrackedLength()),
                () -> assertEquals(testDto.getStatus(), pomodoro.getStatus().name()),
                () -> assertEquals(testDto.getStartedDateTime(), pomodoro.getStartedDateTime().toString()),
                () -> assertEquals(testDto.getLastStart(), pomodoro.getLastStart().toString()),
                () -> assertEquals(testDto.getFinishedDateTime(), pomodoro.getFinishedDateTime().toString())
        );
    }

    @Test
    void ifEntityIsNullDtoShouldBeNull() {
        PomodoroDto pomodoroDto = pomodoroEntityDtoMapper.mapEntityToDto(null);
        assertNull(pomodoroDto);
    }

    @Test
    void ifDtoIsNullEntityShouldBeNull() {
        Pomodoro pomodoro = pomodoroEntityDtoMapper.mapDtoToEntity(null);
        assertNull(pomodoro);
    }
}
