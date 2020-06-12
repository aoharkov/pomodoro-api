package com.petproject.pomodoro.dto.mapper;

import com.petproject.pomodoro.dto.UserDto;
import com.petproject.pomodoro.entity.User;
import org.mapstruct.Mapper;

/**
 * Created by Student on 12.06.2020
 */
@Mapper
public interface UserEntityDtoMapper {
    UserDto mapUserToUserDto(User user);
    User mapUserDtoToUser(UserDto userDto);
}
