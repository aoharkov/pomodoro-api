package com.petproject.pomodoro.dto.mapper;

import com.petproject.pomodoro.dto.CategoryDto;
import com.petproject.pomodoro.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CategoryEntityDtoMapper {
    @Mapping(target = "sphere", source = "category.sphere.title")
    CategoryDto mapEntityToDto(Category category);

    @Mapping(target = "sphere.title", source = "categoryDto.sphere")
    Category mapDtoToEntity(CategoryDto categoryDto);
}
