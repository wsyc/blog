package com.yanchong.blog.Controller;


import com.yanchong.blog.Entity.Blog;
import com.yanchong.blog.Entity.Category;
import com.yanchong.blog.Service.BlogService;
import com.yanchong.blog.Service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CategoryController {


    @Resource
    private CategoryService categoryService;

    @GetMapping("/getCategory")
    public List<Category> Index(){
        return categoryService.getCategoryList();
    }
}
