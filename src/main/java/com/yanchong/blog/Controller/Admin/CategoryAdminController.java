package com.yanchong.blog.Controller.Admin;

import com.yanchong.blog.Entity.Category;
import com.yanchong.blog.Service.CategoryService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/adm")
public class CategoryAdminController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/category")
    public String index(@RequestParam(value = "page", required = false, defaultValue="1") Integer page, ModelMap modelMap) {
        Page<Category> data = categoryService.getPageCategoryList(page);
        HashMap map = new HashMap<String, Object>();
        map.put("counts", data.getTotalElements());
        map.put("pages", data.getTotalPages());
        map.put("content", data.getContent());
        map.put("page", page);
        modelMap.put("data", map);
        return "/admin/category";
    }


    @GetMapping("/addcategory")
    public String addcategory() {
        return "/admin/addcategory";
    }

    @GetMapping("/category-edit")
    public String editcategory(@RequestParam("id") Long id, ModelMap map) {
        Category category = categoryService.getCategoryById(id);
        map.put("category", category);
        return "/admin/editcategory";
    }

    @ResponseBody
    @PostMapping("/update_category")
    public String update_category(@RequestParam("id") Long id, @RequestParam("title") String title, @RequestParam("sort") Integer sort) {
        Category category= new Category();
        category.setId(id);
        category.setTitle(title);
        category.setSort(sort);
        Category category1 = categoryService.updateCategory(category);
        return getString(category1);
    }

    @ResponseBody
    @PostMapping("/delete_category")
    public String delete_category(@RequestParam("id") Long id) {
        Category category= categoryService.getCategoryById(id);
        category.setIsDel(1);
        Category category1 = categoryService.updateCategory(category);
        return getString(category1);
    }

    private String getString(Category category1)  {
        JSONObject result = new JSONObject();
        if (category1.getId() != null){
            result.put("sta",1);
        }else{
            result.put("sta",0);
        }
        return result.toString();
    }

    @ResponseBody
    @PostMapping("/addcategory")
    public String add_category(@RequestParam("title") String title, @RequestParam("sort") Integer sort) {
        Category category= new Category();
        category.setTitle(title);
        category.setSort(sort);
        Category category1 = categoryService.addCategory(category);
        return getString(category1);
    }
}
