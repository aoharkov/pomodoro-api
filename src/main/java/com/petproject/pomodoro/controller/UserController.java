package com.petproject.pomodoro.controller;

import com.petproject.pomodoro.entity.Pomodoro;
import com.petproject.pomodoro.entity.User;
import com.petproject.pomodoro.service.PomodoroService;
import com.petproject.pomodoro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final PomodoroService pomodoroService;

    @Autowired
    public UserController(UserService userService, PomodoroService pomodoroService) {
        this.userService = userService;
        this.pomodoroService = pomodoroService;
    }

    @PostMapping
    public ResponseEntity<Long> createNewUser(@Valid @RequestBody User user) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.save(user));
    }

    @PostMapping("/{id}/pomodoros")
    public ResponseEntity<Long> createNewPomodoro(@PathVariable Long id, @Valid @RequestBody Pomodoro pomodoro) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pomodoroService.save(pomodoro, id));
    }

    @GetMapping
    public ResponseEntity<List<User>> readAllUsers() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> readUserById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(userService.findById(id));
    }

    @GetMapping("/{id}/pomodoros")
    public ResponseEntity<List<Pomodoro>> readAllPomodoros(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(pomodoroService.findByUserId(id));
    }

    @PutMapping
    public ResponseEntity<Void> updateAllUsers(@Valid @RequestBody List<User> users) {
        userService.updateAll(users);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUserById(@PathVariable Long id, @Valid @RequestBody User user) {
        userService.update(user, id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PutMapping("/{id}/pomodoros/{pomodoroId}")
    public ResponseEntity<Void> updatePomodoroForUserWithId(@PathVariable Long id, @PathVariable Long pomodoroId,
                                                            @Valid @RequestBody Pomodoro pomodoro) {
        pomodoroService.update(pomodoro, pomodoroId, id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping("/{id}/pomodoros/{pomodoroId}")
    public ResponseEntity<Void> deletePomodoroForUserWithId(@PathVariable Long id, @PathVariable Long pomodoroId) {
        pomodoroService.delete(pomodoroId, id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
