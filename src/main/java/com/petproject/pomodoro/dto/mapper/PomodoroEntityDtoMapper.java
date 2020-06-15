package com.petproject.pomodoro.dto.mapper;

import com.petproject.pomodoro.dto.PomodoroDto;
import com.petproject.pomodoro.entity.Pomodoro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {CategoryEntityDtoMapper.class, UserEntityDtoMapper.class})
public interface PomodoroEntityDtoMapper {
    @Mapping(target = "userDto", source = "pomodoro.user")
    @Mapping(target = "categoryDto", source = "pomodoro.category")
    PomodoroDto mapEntityToDto(Pomodoro pomodoro);

    @Mapping(target = "user", source = "pomodoroDto.userDto")
    @Mapping(target = "category", source = "pomodoroDto.categoryDto")
    Pomodoro mapDtoToEntity(PomodoroDto pomodoroDto);
}
