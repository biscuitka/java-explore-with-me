package ru.practicum.ewm.service.category.service;

import org.springframework.data.domain.Pageable;
import ru.practicum.ewm.service.category.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAll(Pageable pageable);

    CategoryDto getCategory(long catId);

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto);

    void deleteCategoryById(long catId);
}
