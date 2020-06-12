package com.petproject.pomodoro.dto;

/**
 * Created by Student on 12.06.2020
 */
public class PomodoroDto {
    private String userNickname;
    private String sphereName;
    private String categoryName;
    private String description;
    private int declaredLength;
    private int trackedLength;
    private String status;
    private String startedDateTime;
    private String lastStart;
    private String finishedDateTime;

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getSphereName() {
        return sphereName;
    }

    public void setSphereName(String sphereName) {
        this.sphereName = sphereName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDeclaredLength() {
        return declaredLength;
    }

    public void setDeclaredLength(int declaredLength) {
        this.declaredLength = declaredLength;
    }

    public int getTrackedLength() {
        return trackedLength;
    }

    public void setTrackedLength(int trackedLength) {
        this.trackedLength = trackedLength;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartedDateTime() {
        return startedDateTime;
    }

    public void setStartedDateTime(String startedDateTime) {
        this.startedDateTime = startedDateTime;
    }

    public String getLastStart() {
        return lastStart;
    }

    public void setLastStart(String lastStart) {
        this.lastStart = lastStart;
    }

    public String getFinishedDateTime() {
        return finishedDateTime;
    }

    public void setFinishedDateTime(String finishedDateTime) {
        this.finishedDateTime = finishedDateTime;
    }
}
