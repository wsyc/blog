package com.yanchong.blog.Controller;


import com.yanchong.blog.Entity.Blog;
import com.yanchong.blog.Service.BlogService;
import com.yanchong.blog.Service.LoggerService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.security.provider.MD5;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;


@Controller
public class HomeController {

//    @Resource
//    private UserService userService;
//
    @Resource
    private BlogService blogService;

    @Resource
    private LoggerService loggerService;

    @GetMapping("/time")
    public String List(ModelMap map){
        map.put("LoggerList",loggerService.getLoggerList());
        return "/time";
    }

    @GetMapping("/link")
    public String link(){
        return "/link";
    }

    @GetMapping("/archives")
    public String archives(){
        return "/archives";
    }

    @GetMapping("/category")
    public String category(@RequestParam("id") Long id, @RequestParam(value = "page", required = false, defaultValue = "1") Integer page, ModelMap map){
        Page<Blog> blogsList= blogService.getPageBlogList(page, id);
        map.put("data", blogsList.getContent());
        map.put("pages", blogsList.getTotalPages());
        map.put("count", blogsList.getTotalElements());
        map.put("page", page);
        map.put("id", id);
        return "/category";
    }

    @GetMapping("/gustbook")
    public String gustbook(){
        return "gustbook";
    }

    @GetMapping("/detail")
    public String Info(@RequestParam("id") Long id, ModelMap map){
        blogService.addBlogView(id);
        map.put("data",blogService.getBlogById(id));
        return "/detail";
    }


}
