package com.yanchong.blog.Service;

import com.yanchong.blog.Entity.Blog;
import com.yanchong.blog.Entity.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    List<Category> getCategoryList();

    Category getCategoryById(Long id);

    Page<Category> getPageCategoryList(Integer page);

    Category addCategory(Category category);

    Category updateCategory(Category category);
}
