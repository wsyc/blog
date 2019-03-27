package com.yanchong.blog.Respository;

import com.yanchong.blog.Entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments,Long> {
}
