package com.petproject.pomodoro.controller;

import com.petproject.pomodoro.entity.Pomodoro;
import com.petproject.pomodoro.entity.User;
import com.petproject.pomodoro.service.PomodoroService;
import com.petproject.pomodoro.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @ApiImplicitParams(
            {
                    @ApiImplicitParam(
                            name = "Authorization",
                            required = true,
                            paramType = "header",
                            dataType = "string",
                            value = "authorization header",
                            defaultValue = "Bearer <token>")
            }
    )
    @PostMapping("/{id}/pomodoros")
    public ResponseEntity<Long> createNewPomodoro(@PathVariable Long id, @Valid @RequestBody Pomodoro pomodoro) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pomodoroService.save(pomodoro));
    }

    @ApiImplicitParams(
            {
                    @ApiImplicitParam(
                            name = "Authorization",
                            required = true,
                            paramType = "header",
                            dataType = "string",
                            value = "authorization header",
                            defaultValue = "Bearer <token>")
            }
    )
    @GetMapping
    public ResponseEntity<List<User>> readAllUsers() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(userService.findAll());
    }

    @ApiImplicitParams(
            {
                    @ApiImplicitParam(
                            name = "Authorization",
                            required = true,
                            paramType = "header",
                            dataType = "string",
                            value = "authorization header",
                            defaultValue = "Bearer <token>")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<User> readUserById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(userService.findById(id));
    }

    @ApiImplicitParams(
            {
                    @ApiImplicitParam(
                            name = "Authorization",
                            required = true,
                            paramType = "header",
                            dataType = "string",
                            value = "authorization header",
                            defaultValue = "Bearer <token>")
            }
    )
    @GetMapping("/{id}/pomodoros")
    public ResponseEntity<List<Pomodoro>> readAllPomodoros(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(pomodoroService.findByUserId(id));
    }

    @ApiImplicitParams(
            {
                    @ApiImplicitParam(
                            name = "Authorization",
                            required = true,
                            paramType = "header",
                            dataType = "string",
                            value = "authorization header",
                            defaultValue = "Bearer <token>")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUserById(@PathVariable Long id, @Valid @RequestBody User user) {
        userService.update(user);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @ApiImplicitParams(
            {
                    @ApiImplicitParam(
                            name = "Authorization",
                            required = true,
                            paramType = "header",
                            dataType = "string",
                            value = "authorization header",
                            defaultValue = "Bearer <token>")
            }
    )
    @PutMapping("/{id}/pomodoros/{pomodoroId}")
    public ResponseEntity<Void> updatePomodoro(@Valid @RequestBody Pomodoro pomodoro) {
        pomodoroService.update(pomodoro);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @ApiImplicitParams(
            {
                    @ApiImplicitParam(
                            name = "Authorization",
                            required = true,
                            paramType = "header",
                            dataType = "string",
                            value = "authorization header",
                            defaultValue = "Bearer <token>")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @ApiImplicitParams(
            {
                    @ApiImplicitParam(
                            name = "Authorization",
                            required = true,
                            paramType = "header",
                            dataType = "string",
                            value = "authorization header",
                            defaultValue = "Bearer <token>")
            }
    )
    @DeleteMapping("/{id}/pomodoros/{pomodoroId}")
    public ResponseEntity<Void> deletePomodoroForUserWithId(@PathVariable Long id, @PathVariable Long pomodoroId) {
        pomodoroService.delete(pomodoroId, id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
