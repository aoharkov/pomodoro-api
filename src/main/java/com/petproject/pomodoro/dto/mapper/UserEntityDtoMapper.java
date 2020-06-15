package com.petproject.pomodoro.dto.mapper;

import com.petproject.pomodoro.dto.UserDto;
import com.petproject.pomodoro.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Created by Student on 15.06.2020
 */
@Mapper
public interface UserEntityDtoMapper {
    @Mappings({
            @Mapping(target = "defaultPomodoroLength", source = "user.defaultLenght")
    })
    UserDto mapEntityToDto(User user);

    @Mappings({
            @Mapping(target = "defaultLenght", source = "userDto.defaultPomodoroLength")
    })
    User mapDtoToEntity(UserDto userDto);
}
