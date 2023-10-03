package ru.practicum.ewm.service.category.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.service.category.dto.CategoryDto;
import ru.practicum.ewm.service.category.service.CategoryService;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/admin/categories")
public class CategoryControllerAdmin {
    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto create(@Valid @RequestBody CategoryDto categoryDto) {
        log.info("Создание категории: {}", categoryDto);
        return categoryService.createCategory(categoryDto);
    }

    @PatchMapping("/{catId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto update(@Valid @RequestBody CategoryDto categoryDto, @PathVariable long catId) {
        log.info("Обновление категории: {}", categoryDto);
        return categoryService.updateCategory(categoryDto, catId);
    }

    @DeleteMapping("/{catId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable long catId) {
        categoryService.deleteCategoryById(catId);
    }
}
