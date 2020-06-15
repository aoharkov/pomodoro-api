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
public class PomodoroDto {
    private String userEmail;
    private String sphere;
    private String category;
    private String description;
    private int declaredLength;
    private int trackedLength;
    private String status;
    private String startedDateTime;
    private String lastStart;
    private String finishedDateTime;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getSphere() {
        return sphere;
    }

    public void setSphere(String sphere) {
        this.sphere = sphere;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
