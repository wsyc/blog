package com.yanchong.blog.Entity;

import org.hibernate.annotations.Cascade;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pic")
@Component
public class Pic implements Serializable {

    private static final long serialVersionUID = 1L;

    public Pic() {
    }

    private Long id;

    private String picUrl;

    private String md5;

//    @OneToMany(targetEntity=Blog.class)	//一对多，让Employee维护外键
//    @OneToOne(mappedBy="Pic",fetch=FetchType.EAGER)
//    private Blog blogs;
//
//    public Blog getBlogs() {
//        return blogs;
//    }
//
//    public void setBlogs(Blog blogs) {
//        this.blogs = blogs;
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

}
