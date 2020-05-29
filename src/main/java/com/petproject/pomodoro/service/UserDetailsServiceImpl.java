package com.petproject.pomodoro.service;

import com.petproject.pomodoro.config.UserPrincipal;
import com.petproject.pomodoro.entity.User;
import com.petproject.pomodoro.exceptions.UserNotFoundException;
import com.petproject.pomodoro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository
                            .findByNickname(username)
                            .orElseThrow(() -> new UserNotFoundException("User with username:" + username + "is not found"));
        return new UserPrincipal(user);
    }
}
