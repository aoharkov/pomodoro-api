package com.petproject.pomodoro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Student on 17.06.2020
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDto {
    private long id;
    private String sphere;
    private String title;
}
