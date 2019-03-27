package com.yanchong.blog.Respository;

import com.yanchong.blog.Entity.Pic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PicRepository extends JpaRepository<Pic,Long> {
    public Pic findByMd5(String md5);
}
