package com.petproject.pomodoro.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CategoryTest {
    private final Category category1 = Category.builder()
            .title("work")
            .build();
    private final Category category2 = Category.builder()
            .title("study")
            .build();

    @Test
    void compareToShouldReturnPositiveNumber() {
        assertTrue(category1.compareTo(category2) > 0);
    }
}
