package com.yanchong.blog.Entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserVo {

    @NotEmpty(message="用户名不能为空！")
    private String name;

    @Size(min=6,max=10,message = "密码长度必须6到10位")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
