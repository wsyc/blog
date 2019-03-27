package com.yanchong.blog.Respository;

import com.yanchong.blog.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
