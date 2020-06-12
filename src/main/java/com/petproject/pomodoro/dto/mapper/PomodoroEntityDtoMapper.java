package com.petproject.pomodoro.dto.mapper;

import com.petproject.pomodoro.dto.PomodoroDto;
import com.petproject.pomodoro.entity.Pomodoro;
import org.mapstruct.Mapper;

/**
 * Created by Student on 12.06.2020
 */
@Mapper
public interface PomodoroEntityDtoMapper {
    PomodoroDto mapPomodoroToPomodoroDto(Pomodoro pomodoro);
    Pomodoro mapPomodoroDtoToPomodoro(PomodoroDto pomodoroDto);
}
