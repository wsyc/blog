package com.yanchong.blog.Respository;

import com.yanchong.blog.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findByUsername(String userName);
}
