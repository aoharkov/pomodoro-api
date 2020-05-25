package com.petproject.pomodoro.service;

import com.petproject.pomodoro.entity.Pomodoro;

import java.util.List;

public interface PomodoroService {

    Long save(Pomodoro pomodoro, Long userId);

    List<Pomodoro> findByUserId(Long userId);

    void update(Pomodoro pomodoro);

    void update(Pomodoro pomodoro, Long id, Long userId);

    void delete(Long id, Long userId);
}
