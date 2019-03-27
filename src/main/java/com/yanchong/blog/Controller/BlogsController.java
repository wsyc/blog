package com.yanchong.blog.Controller;


import com.yanchong.blog.Entity.Blog;
import com.yanchong.blog.Service.BlogService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class BlogsController {


    @Resource
    private BlogService blogService;
    /**
     *
     * @param model
     * @return
     */
    @GetMapping(value = {"/", "/index"})
    public String Index(ModelMap model){
//        Page<Blog> userList = blogService.getBlogHomeList();
//        model.addAttribute("userList", userList);
        List<Blog> blog= blogService.getBlogHomeList();
        model.addAttribute("blogList", blog);
        return "/index";
    }
}
