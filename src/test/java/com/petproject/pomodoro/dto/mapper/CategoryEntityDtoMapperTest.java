package com.petproject.pomodoro.dto.mapper;

import com.petproject.pomodoro.dto.CategoryDto;
import com.petproject.pomodoro.entity.Category;
import com.petproject.pomodoro.entity.Sphere;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class CategoryEntityDtoMapperTest {
    private static final CategoryEntityDtoMapper categoryEntityDtoMapper = Mappers
            .getMapper(CategoryEntityDtoMapper.class);
    private static final Long ID = 1L;
    private static final String SPHERE_TITLE = "Sphere Title";
    private static final String CATEGORY_TITLE = "Category Title";

    private final Sphere sphere = Sphere.builder()
            .id(ID)
            .title(SPHERE_TITLE)
            .build();

    private final Category testEntity = Category.builder()
            .id(ID)
            .user(null)
            .sphere(sphere)
            .title(CATEGORY_TITLE)
            .build();

    private final CategoryDto testDto = CategoryDto.builder()
            .id(ID)
            .sphere(SPHERE_TITLE)
            .title(CATEGORY_TITLE)
            .build();

    @Test
    void afterMappingEntityToDtoFieldsMustBeEquals() {
        CategoryDto categoryDto = categoryEntityDtoMapper.mapEntityToDto(testEntity);
        assertAll(
                () -> assertEquals(testEntity.getId(), categoryDto.getId()),
                () -> assertEquals(testEntity.getSphere().getTitle(), categoryDto.getSphere()),
                () -> assertEquals(testEntity.getTitle(), categoryDto.getTitle())
        );
    }

    @Test
    void afterMappingDtoToEntityFieldsMustBeEquals() {
        Category category = categoryEntityDtoMapper.mapDtoToEntity(testDto);
        assertAll(
                () -> assertEquals(testDto.getId(), category.getId()),
                () -> assertEquals(testDto.getSphere(), category.getSphere().getTitle()),
                () -> assertEquals(testDto.getTitle(), category.getTitle())
        );
    }

    @Test
    void ifEntityIsNullDtoShouldBeNull() {
        CategoryDto categoryDto = categoryEntityDtoMapper.mapEntityToDto(null);
        assertNull(categoryDto);
    }

    @Test
    void ifDtoIsNullEntityShouldBeNull() {
        Category category = categoryEntityDtoMapper.mapDtoToEntity(null);
        assertNull(category);
    }
}
