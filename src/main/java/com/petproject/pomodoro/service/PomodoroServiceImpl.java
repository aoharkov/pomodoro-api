package com.petproject.pomodoro.service;

import com.petproject.pomodoro.entity.Pomodoro;
import com.petproject.pomodoro.exceptions.BadRequestException;
import com.petproject.pomodoro.repository.PomodoroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PomodoroServiceImpl implements PomodoroService {
    private final PomodoroRepository pomodoroRepository;

    @Autowired
    public PomodoroServiceImpl(PomodoroRepository pomodoroRepository) {
        this.pomodoroRepository = pomodoroRepository;
    }

    @Override
    public Long save(Pomodoro pomodoro) {
        return pomodoroRepository.save(pomodoro).getId();
    }

    @Override
    public List<Pomodoro> findByUserId(Long userId) {
        return pomodoroRepository.findByUserId(userId);
    }

    @Override
    public void update(Pomodoro pomodoro) {
        pomodoroRepository.save(pomodoro);
    }

    @Override
    public void delete(Long id, Long userId) {
        Optional<Pomodoro> pomodoroOptional = pomodoroRepository.findById(id);
        if (pomodoroOptional.isPresent()) {
            Pomodoro pomodoro = pomodoroOptional.get();
            if (pomodoro.getUserId().equals(userId)) {
                pomodoroRepository.delete(pomodoro);
            } else {
                throw new BadRequestException();
            }
        }
    }
}
