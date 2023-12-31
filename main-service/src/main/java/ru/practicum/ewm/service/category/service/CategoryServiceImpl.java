package ru.practicum.ewm.service.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.service.category.dto.CategoryDto;
import ru.practicum.ewm.service.category.mapper.CategoryMapper;
import ru.practicum.ewm.service.category.model.Category;
import ru.practicum.ewm.service.category.repository.CategoryRepository;
import ru.practicum.ewm.service.exception.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> getAll(Pageable pageable) {
        List<Category> categories = categoryRepository.findAll(pageable).getContent();
        return categoryMapper.fromListCategoriesToDto(categories);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDto getCategory(long catId) {
        Category category = getCategoryOrElseThrow(catId);
        return categoryMapper.fromCategoryToDto(category);
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = categoryMapper.fromDtoToCategory(categoryDto);
        Category createdCategory = categoryRepository.save(category);
        return categoryMapper.fromCategoryToDto(createdCategory);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        long catId = categoryDto.getId();
        Category categoryInRepo = getCategoryOrElseThrow(catId);
        if (categoryDto.getName() != null) {
            categoryInRepo.setName(categoryDto.getName());
        }
        Category updatedCategory = categoryRepository.save(categoryInRepo);
        return categoryMapper.fromCategoryToDto(updatedCategory);
    }

    @Override
    public void deleteCategoryById(long catId) {
        getCategoryOrElseThrow(catId);
        categoryRepository.deleteById(catId);
    }

    private Category getCategoryOrElseThrow(long catId) {
        return categoryRepository.findById(catId)
                .orElseThrow(() -> new NotFoundException("Category with id=" + catId + " was not found"));
    }
}
