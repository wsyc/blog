package com.yanchong.blog.Service;

import com.yanchong.blog.Entity.Blog;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BlogService {
    List<Blog> getBlogList();

    List<Blog> getBlogHomeList();

    Blog getBlogById(Long id);

    Page<Blog> getPageBlogList(Integer page);

    Page<Blog> getPageBlogList(Integer page, Long category_id);

    Blog addUserInfo(Blog blog);

    Blog updateBlog(Blog blog);

    Blog addBlogView(Long id);
}
