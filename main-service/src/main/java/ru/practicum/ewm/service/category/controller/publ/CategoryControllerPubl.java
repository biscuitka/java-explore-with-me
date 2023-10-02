package ru.practicum.ewm.service.category.controller.publ;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.service.category.dto.CategoryDto;
import ru.practicum.ewm.service.category.service.CategoryService;
import ru.practicum.ewm.service.constants.HeaderConstants;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/categories")
public class CategoryControllerPubl {
    private final CategoryService categoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDto> getAll(@RequestParam(defaultValue = HeaderConstants.DEFAULT_FROM_VALUE) @Min(0) int from,
                                    @RequestParam(defaultValue = HeaderConstants.DEFAULT_SIZE_VALUE) int size) {
        Pageable pageable = PageRequest.of(from / size, size);
        {
            return categoryService.getAll(pageable);
        }
    }

    @GetMapping("/{catId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto getCategory(@PathVariable long catId) {
        return categoryService.getCategory(catId);
    }
}
