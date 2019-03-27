package com.yanchong.blog.Service;

import com.yanchong.blog.Entity.Category;
import com.yanchong.blog.Entity.Pic;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PicService {
    List<Pic> getPicList();

    Pic getPicById(Long id);

    Pic getPicByMd5(String md5);

    Pic addPic(Pic pic);

    Pic updatePic(Pic pic);
}
