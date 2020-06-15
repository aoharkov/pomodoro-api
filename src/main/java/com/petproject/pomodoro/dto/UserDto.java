package com.petproject.pomodoro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * Created by Student on 15.06.2020
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String email;
    private String nickname;
    private String role;
    private String registrationDate;
    private int defaultPomodoroLength;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getDefaultPomodoroLength() {
        return defaultPomodoroLength;
    }

    public void setDefaultPomodoroLength(int defaultPomodoroLength) {
        this.defaultPomodoroLength = defaultPomodoroLength;
    }
}
