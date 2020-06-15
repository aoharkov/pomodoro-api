package com.petproject.pomodoro.dto.mapper;

import com.petproject.pomodoro.dto.PomodoroDto;
import com.petproject.pomodoro.entity.Pomodoro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Created by Student on 15.06.2020
 */
@Mapper
public interface PomodoroEntityDtoMapper {
    @Mappings({
            @Mapping(target = "user.email", source = "pomodoroDto.userEmail"),
            @Mapping(target = "category.sphere.title", source = "pomodoroDto.sphere"),
            @Mapping(target = "category.title", source = "pomodoroDto.category"),
    })
    Pomodoro mapDtoToEntity(PomodoroDto pomodoroDto);

    @Mappings({
            @Mapping(target = "userEmail", source = "pomodoro.user.email"),
            @Mapping(target = "sphere", source = "pomodoro.category.sphere.title"),
            @Mapping(target = "category", source = "pomodoro.category.title"),
    })
    PomodoroDto mapEntityToDto(Pomodoro pomodoro);
}
