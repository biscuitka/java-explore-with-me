package ru.practicum.ewm.service.category.mapper;

import org.mapstruct.Mapper;
import ru.practicum.ewm.service.category.dto.CategoryDto;
import ru.practicum.ewm.service.category.model.Category;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto fromCategoryToDto(Category category);

    Category fromDtoToCategory(CategoryDto categoryDto);

    List<CategoryDto> fromListCategoriesToDto(List<Category> categories);
}
