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
public class UserDto {
    private long id;
    private String nickname;
    private String role;
    private ZonedDateTime registrationDate;
    private int defaultPomodoroLength;
}
