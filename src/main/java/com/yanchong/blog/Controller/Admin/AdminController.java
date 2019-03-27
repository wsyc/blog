package com.yanchong.blog.Controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adm")
public class AdminController {
    @GetMapping(value = {"/", "/index"})
    public String Index(){
        return "/admin/index";
    }

    @GetMapping("/welcome.html")
    public String welcome(){
        return "/admin/welcome";
    }
}
