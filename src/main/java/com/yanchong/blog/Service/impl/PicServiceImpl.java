package com.yanchong.blog.Service.impl;

import com.yanchong.blog.Entity.Category;
import com.yanchong.blog.Entity.Pic;
import com.yanchong.blog.Respository.CategoryRepository;
import com.yanchong.blog.Respository.PicRepository;
import com.yanchong.blog.Service.CategoryService;
import com.yanchong.blog.Service.PicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PicServiceImpl implements PicService {

    private final PicRepository picRepository;

    @Autowired
    public PicServiceImpl(PicRepository picRepository) {
        this.picRepository = picRepository;
    }


    @Override
    public List<Pic> getPicList() {
        return picRepository.findAll();
    }

    @Override
    public Pic getPicById(Long id) {
        return picRepository.findById(id).get();
    }
    @Override
    public Pic getPicByMd5(String md5) {
        return picRepository.findByMd5(md5);
    }

    @Override
    public Pic addPic(Pic pic) {
        return picRepository.save(pic);
    }

    @Override
    public Pic updatePic(Pic pic) {
        return picRepository.save(pic);
    }
}
