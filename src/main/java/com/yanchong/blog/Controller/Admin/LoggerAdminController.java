package com.yanchong.blog.Controller.Admin;

import com.yanchong.blog.Entity.Blog;
import com.yanchong.blog.Entity.Category;
import com.yanchong.blog.Entity.Logger;
import com.yanchong.blog.Service.BlogService;
import com.yanchong.blog.Service.CategoryService;
import com.yanchong.blog.Service.LoggerService;
import com.yanchong.blog.Service.PicService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/adm")
public class
LoggerAdminController {

    @Resource
    private LoggerService loggerService;

    @GetMapping("/logger")
    public String index(@RequestParam(value = "page", required = false, defaultValue="1") Integer page, ModelMap modelMap) {
        Page<Logger> data = loggerService.getPageLoggerList(page);
        HashMap map = new HashMap<String, Object>();
        map.put("counts", data.getTotalElements());
        map.put("pages", data.getTotalPages());
        map.put("content", data.getContent());
        map.put("page", page);
        modelMap.put("data", map);
        return "/admin/logger";
    }


    @GetMapping("/addlogger")
    public String addblog(ModelMap map) {
        return "/admin/addlogger";
    }

    @GetMapping("/logger-edit")
    public String editlogger(@RequestParam("id") Long id, ModelMap map) {
        Logger logger = loggerService.getLoggerById(id);
        map.put("logger", logger);
        return "/admin/logger-edit";
    }

//    @ResponseBody
//    @PostMapping("/delete_blog")
//    public String delete_blog(@RequestParam("id") Long id) {
//        Logger logger = loggerService.getLoggerById(id);
//        logger.setIsDel(1);
//        Logger blog1 = loggerService.updateLogger(logger);
//        return getString(blog1.getId());
//    }

//    @ResponseBody
//    @PostMapping("/blog-edit")
//    public String update_blog(@RequestParam("id") Long id, @RequestParam("title") String title, @RequestParam("smarttitle") String smarttitle, @RequestParam("type") Integer type, @RequestParam("category_id") Long category_id, @RequestParam("content") String content, @RequestParam(value = "pic_id", required = false, defaultValue = "0") Long pic_id) {
//        Blog blog = blogService.getBlogById(id);
//        blog.setTitle(title);
//        String SmartTitle = smarttitle.length()<20 ? smarttitle : smarttitle.substring(0,20);
//        blog.setSmartTitle(SmartTitle);
//        blog.setType(type);
//        blog.setContent(content);
//        blog.setPic(picService.getPicById(pic_id));
//        blog.setCategory(categoryService.getCategoryById(category_id));
//        blogService.updateBlog(blog);
//        return getString(blog.getId());
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
    @PostMapping("/add_logger")
    public String add_blog(@RequestParam("title") String title, @RequestParam("type") Integer type) {
        Logger logger = new Logger();
        logger.setTitle(title);
        logger.setType(type);
        logger.setTime(new Date());
        logger.setIsDel(0);
        Logger logger1 = loggerService.addLogger(logger);
        return getString(logger1.getId());
    }
    @ResponseBody
    @PostMapping("/update_logger")
    public String update_blog(@RequestParam("id") Long id, @RequestParam("title") String title, @RequestParam("type") Integer type) {
        Logger logger = loggerService.getLoggerById(id);
        logger.setTitle(title);
        logger.setType(type);
        logger.setIsDel(0);
        Logger logger1 = loggerService.updateLogger(logger);
        return getString(logger1.getId());
    }
}
