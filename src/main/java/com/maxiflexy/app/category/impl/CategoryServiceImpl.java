package com.maxiflexy.app.category.impl;

import com.maxiflexy.app.category.Category;
import com.maxiflexy.app.category.CategoryRepository;
import com.maxiflexy.app.category.CategoryService;
import com.maxiflexy.app.category.request.CategoryRequest;
import com.maxiflexy.app.category.request.CategoryUpdateRequest;
import com.maxiflexy.app.category.response.CategoryResponse;
import com.maxiflexy.app.exception.BusinessException;
import com.maxiflexy.app.exception.ErrorCode;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public String createCategory(final CategoryRequest request, final String userId) {
        checkCategoryUnicityForUser(request.getName(), userId);

        final Category category = this.categoryMapper.toCategory(request);

        return this.categoryRepository.save(category)
                .getId();
    }

    @Override
    public void updateCategory(final CategoryUpdateRequest request, final String catId, final String userId) {
        final Category categoryToUpdate = this.categoryRepository.findById(catId)
                .orElseThrow(() -> new EntityNotFoundException("No category found with id: " + catId));

        checkCategoryUnicityForUser(request.getName(), userId);

        this.categoryMapper.mergeCategory(categoryToUpdate, request);
        this.categoryRepository.save(categoryToUpdate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> findAllByOwner(final String userId) {
        return this.categoryRepository.findAllByUserId(userId)
                .stream()
                .map(this.categoryMapper::toCategoryResponse)
                .toList();
    }

    @Override
    public CategoryResponse findCategoryById(final String catId) {
        return this.categoryRepository.findById(catId)
                .map(this.categoryMapper::toCategoryResponse)
                .orElseThrow(() -> new EntityNotFoundException("No category found with id: " + catId));
    }

    @Override
    public void deleteCategoryById(final String catId) {
        // todo
        // mark the category for deletion
        // the scheduler should pick up all the marked categories and perform the deletion
    }

    private void checkCategoryUnicityForUser(final String name, final String userId) {
        final boolean alreadyExistsForUser = this.categoryRepository.findByNameAndUser(name, userId);
        if (alreadyExistsForUser) {
            throw new BusinessException(ErrorCode.CATEGORY_ALREADY_EXISTS_FOR_USER);
        }
    }
}