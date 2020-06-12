package com.petproject.pomodoro.dto;

/**
 * Created by Student on 12.06.2020
 */
public class UserDto {
    private String email;
    private String nickname;
    private String password;
    private String role;
    private String registrationDate;
    private int defaultLenght;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getDefaultLenght() {
        return defaultLenght;
    }

    public void setDefaultLenght(int defaultLenght) {
        this.defaultLenght = defaultLenght;
    }
}
