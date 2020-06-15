package com.petproject.pomodoro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

/**
 * Created by Student on 15.06.2020
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PomodoroDto {
    private long id;
    private UserDto userDto;
    private CategoryDto categoryDto;
    private String description;
    private int declaredLength;
    private int trackedLength;
    private String status;
    private ZonedDateTime startedDateTime;
    private ZonedDateTime lastStart;
    private ZonedDateTime finishedDateTime;
}
