package com.petproject.pomodoro.dto.mapper;

import com.petproject.pomodoro.dto.UserDto;
import com.petproject.pomodoro.entity.Role;
import com.petproject.pomodoro.entity.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityDtoMapperTest {
    private static final UserEntityDtoMapper userEntityDtoMapper = Mappers
            .getMapper(UserEntityDtoMapper.class);
    private static final ZonedDateTime registrationDate = ZonedDateTime.of(2019, 5, 20, 9,
            30, 20, 0, ZoneId.of("UTC"));
    private static final Long USER_ID = 1L;
    private static final String USER_EMAIL = "pomodoro@gmail.com";
    private static final String USER_NICKNAME = "pomodoro_user";
    private static final String USER_PASSWORD = "qwerty12345";
    private static final Role USER_ROLE = Role.USER;
    private static final int DEFAULT_LENGTH = 25;

    private final User testEntity = User.builder()
            .id(USER_ID)
            .email(USER_EMAIL)
            .nickname(USER_NICKNAME)
            .password(USER_PASSWORD)
            .role(USER_ROLE)
            .registrationDate(registrationDate)
            .defaultLenght(DEFAULT_LENGTH)
            .build();

    private final UserDto testDto = UserDto.builder()
            .id(USER_ID)
            .nickname(USER_NICKNAME)
            .role(USER_ROLE.name())
            .registrationDate(registrationDate)
            .defaultPomodoroLength(DEFAULT_LENGTH)
            .build();

    @Test
    void afterMappingEntityToDtoFieldsMustBeEquals() {
        UserDto userDto = userEntityDtoMapper.mapEntityToDto(testEntity);
        assertAll(
                () -> assertEquals(testEntity.getId(), userDto.getId()),
                () -> assertEquals(testEntity.getNickname(), userDto.getNickname()),
                () -> assertEquals(testEntity.getRole().name(), userDto.getRole()),
                () -> assertEquals(testEntity.getRegistrationDate(), userDto.getRegistrationDate()),
                () -> assertEquals(testEntity.getDefaultLenght(), userDto.getDefaultPomodoroLength())
        );
    }

    @Test
    void afterMappingDtoToEntityFieldsMustBeEquals() {
        User user = userEntityDtoMapper.mapDtoToEntity(testDto);
        assertAll(
                () -> assertEquals(testDto.getId(), user.getId()),
                () -> assertEquals(testDto.getNickname(), user.getNickname()),
                () -> assertEquals(testDto.getRole(), user.getRole().name()),
                () -> assertEquals(testDto.getRegistrationDate(), user.getRegistrationDate()),
                () -> assertEquals(testDto.getDefaultPomodoroLength(), user.getDefaultLenght())
        );
    }

    @Test
    void ifEntityIsNullDtoShouldBeNull() {
        UserDto userDto = userEntityDtoMapper.mapEntityToDto(null);
        assertNull(userDto);
    }

    @Test
    void ifDtoIsNullEntityShouldBeNull() {
        User user = userEntityDtoMapper.mapDtoToEntity(null);
        assertNull(user);
    }
}
