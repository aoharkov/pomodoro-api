package com.petproject.pomodoro.service;

import com.petproject.pomodoro.entity.User;

import java.util.List;

public interface UserService {

    Long save(User user);

    User findById(Long id);

    List<User> findAll();

    void update(User user);

    void update(User user, Long id);

    void updateAll(List<User> users);

    void delete(Long id);
}
