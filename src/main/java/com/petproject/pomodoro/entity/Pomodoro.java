package com.petproject.pomodoro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pomodoros")
public class Pomodoro {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "description")
    private String description;

    @Column(name = "length", nullable = false)
    private Integer length;

    @Column(name = "complitedDate")
    private LocalDateTime complitedDate;

    public Pomodoro(Long userId, String description, Integer length, LocalDateTime complitedDate) {
        this.userId = userId;
        this.description = description;
        this.length = length;
        this.complitedDate = complitedDate;
    }
}
