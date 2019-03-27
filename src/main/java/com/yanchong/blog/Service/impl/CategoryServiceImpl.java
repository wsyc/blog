package com.yanchong.blog.Service.impl;

import com.yanchong.blog.Entity.Blog;
import com.yanchong.blog.Entity.Category;
import com.yanchong.blog.Respository.BlogRepository;
import com.yanchong.blog.Respository.CategoryRepository;
import com.yanchong.blog.Service.BlogService;
import com.yanchong.blog.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategoryList() {
        Sort sort= new Sort(Sort.Direction.DESC, "sort");
        return categoryRepository.findAll(sort);
    }

    @Override
    public Category getCategoryById(Long id) {
        Category one = categoryRepository.findById(id).get();
        return one;
    }

    @Override
    public Page<Category> getPageCategoryList(Integer page) {
        Sort sort= new Sort(Sort.Direction.DESC, "sort");
        PageRequest pageRequest = PageRequest.of(page-1,10);
        return categoryRepository.findAll(pageRequest);
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }
}
