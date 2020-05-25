package com.petproject.pomodoro.service;

import com.petproject.pomodoro.entity.User;
import com.petproject.pomodoro.exceptions.BadRequestException;
import com.petproject.pomodoro.exceptions.NoSuchElementException;
import com.petproject.pomodoro.exceptions.SuchElementAlreadyExistsException;
import com.petproject.pomodoro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Long save(User user) {
        if (!userRepository.findByNickname(user.getNickname()).isPresent()) {
            return userRepository.save(user).getId();
        } else {
            throw new SuchElementAlreadyExistsException();
        }
    }

    @Override
    public User findById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public void update(User user, Long id) {
        if (!id.equals(user.getId())) {
            throw new BadRequestException();
        }
        update(user);
    }

    @Override
    public void updateAll(List<User> users) {
        users.forEach(this::update);
    }

    @Override
    public void delete(Long id) {
        userRepository.findById(id).ifPresent(userRepository::delete);
    }
}
