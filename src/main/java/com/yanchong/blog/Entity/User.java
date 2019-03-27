package com.yanchong.blog.Entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Component
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    public User() {
    }

    private Long id;

    private String username;

    private String password;

    private Integer sta;

    private String title;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSta() {
        return sta;
    }

    public void setSta(int sta) {
        this.sta = sta;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
