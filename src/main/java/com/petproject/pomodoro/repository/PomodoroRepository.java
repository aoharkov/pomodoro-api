package com.petproject.pomodoro.repository;

import com.petproject.pomodoro.entity.Pomodoro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PomodoroRepository extends JpaRepository<Pomodoro, Long> {

    List<Pomodoro> findByUserId(Long userId);
}
