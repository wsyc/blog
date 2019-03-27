package com.yanchong.blog.Controller.Admin;

import com.yanchong.blog.Entity.Blog;
import com.yanchong.blog.Entity.Category;
import com.yanchong.blog.Service.BlogService;
import com.yanchong.blog.Service.CategoryService;
import com.yanchong.blog.Service.PicService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/adm")
public class BlogAdminController {

    @Resource
    private BlogService blogService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private PicService picService;

    @GetMapping("/blog")
    public String index(@RequestParam(value = "page", required = false, defaultValue="1") Integer page, ModelMap modelMap) {
        Page<Blog> data = blogService.getPageBlogList(page);
        HashMap map = new HashMap<String, Object>();
        map.put("counts", data.getTotalElements());
        map.put("pages", data.getTotalPages());
        map.put("content", data.getContent());
        map.put("page", page);
        modelMap.put("data", map);
        return "/admin/blog";
    }


    @GetMapping("/addblog")
    public String addblog(ModelMap map) {
        List<Category> categoryList = categoryService.getCategoryList();
        map.put("categoryList", categoryList);
        return "/admin/addblog";
    }

    @GetMapping("/blog-edit")
    public String editblog(@RequestParam("id") Long id, ModelMap map) {
        Blog blog = blogService.getBlogById(id);
        List<Category> categoryList = categoryService.getCategoryList();
        map.put("categoryList", categoryList);
        map.put("blog", blog);
        return "/admin/edit-blog";
    }

    @ResponseBody
    @PostMapping("/delete_blog")
    public String delete_blog(@RequestParam("id") Long id) {
        Blog blog= blogService.getBlogById(id);
        blog.setIsDel(1);
        Blog blog1 = blogService.updateBlog(blog);
        return getString(blog1.getId());
    }

    @ResponseBody
    @PostMapping("/blog-edit")
    public String update_blog(@RequestParam("id") Long id, @RequestParam("title") String title, @RequestParam("smarttitle") String smarttitle, @RequestParam("type") Integer type, @RequestParam("category_id") Long category_id, @RequestParam("content") String content, @RequestParam(value = "pic_id", required = false, defaultValue = "0") Long pic_id) {
        Blog blog = blogService.getBlogById(id);
        blog.setTitle(title);
        String SmartTitle = smarttitle.length()<20 ? smarttitle : smarttitle.substring(0,20);
        blog.setSmartTitle(SmartTitle);
        blog.setType(type);
        blog.setContent(content);
        blog.setPic(picService.getPicById(pic_id));
        blog.setCategory(categoryService.getCategoryById(category_id));
        blogService.updateBlog(blog);
        return getString(blog.getId());
    }

//    @ResponseBody
//    @PostMapping("/delete_category")
//    public String delete_category(@RequestParam("id") Long id) throws JSONException {
//        Category category= categoryService.getCategoryById(id);
//        category.setIsDel(1);
//        Category category1 = categoryService.updateCategory(category);
//        return getString(category1);
//    }

    private String getString(Long id){
        JSONObject result = new JSONObject();
        if (id != null){
            result.put("sta",1);
        }else{
            result.put("sta",0);
        }
        return result.toString();
    }

    @ResponseBody
    @PostMapping("/add_blog")
    public String add_blog(@RequestParam("title") String title, @RequestParam("smarttitle") String smarttitle, @RequestParam("type") Integer type, @RequestParam("category_id") Long category_id, @RequestParam("content") String content, @RequestParam(value = "pic_id", required = false, defaultValue = "0") Long pic_id) {
        Blog blog = new Blog();
        blog.setTitle(title);
        String SmartTitle = smarttitle.length()<20 ? smarttitle : smarttitle.substring(0,20);
        blog.setSmartTitle(SmartTitle);
        blog.setType(type);
        blog.setContent(content);
        blog.setPic(picService.getPicById(pic_id));
        blog.setCategory(categoryService.getCategoryById(category_id));

        Blog blog1 = blogService.addUserInfo(blog);

        return getString(blog1.getId());
    }
}
