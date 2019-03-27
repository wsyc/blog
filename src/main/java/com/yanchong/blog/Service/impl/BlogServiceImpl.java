package com.yanchong.blog.Service.impl;

import com.yanchong.blog.Entity.Blog;
import com.yanchong.blog.Entity.Category;
import com.yanchong.blog.Respository.BlogRepository;
import com.yanchong.blog.Respository.CategoryRepository;
import com.yanchong.blog.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Autowired
    private CategoryRepository categoryRepository;
    /**
     * 获取所有博客
     * @return List<Blog>
     */
    public List<Blog> getBlogHomeList() {
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        Pageable pageable;
        pageable = PageRequest.of(0,5, sort);
        return blogRepository.findAll(pageable).getContent();
    }

    /**
     * 获取所有博客
     * @return List<Blog>
     */
    public List<Blog> getBlogList() {
        return blogRepository.findAll();
    }

    public Blog getBlogById(Long id) {
        return blogRepository.findById(id).get();
    }

    public Blog addBlogView(Long id) {
        Blog blog = blogRepository.findById(id).get();
        blog.setView_num(blog.getView_num()+1);
        return blogRepository.save(blog);
    }

    public Blog addUserInfo(Blog user) {
        return blogRepository.save(user);
    }

    public Blog updateBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    public void deleteBlogInfoById(Long Id) {
        blogRepository.deleteById(Id);
    }

    public Page<Blog> getPageBlogList(Integer page) {
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        Pageable pageable;
        pageable = PageRequest.of(page-1,10,sort);
        return blogRepository.findAll(pageable);
    }

    public Page<Blog> getPageBlogList(Integer page, Long category_id) {
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        Pageable pageable;
        pageable = PageRequest.of(page-1,10,sort);
        Category category = categoryRepository.findById(category_id).get();
        //查询条件构造
        Specification<Blog> spec = (Specification<Blog>) (root, query, cb) -> {
                Path<Category> scategory = root.get("category");
            Predicate b= cb.equal(scategory, category);
                return b;
               };
        return blogRepository.findAll(spec, pageable);
    }
}
