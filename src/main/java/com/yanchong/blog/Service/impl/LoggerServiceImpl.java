package com.yanchong.blog.Service.impl;

import com.yanchong.blog.Entity.Logger;
import com.yanchong.blog.Respository.LoggerRepository;
import com.yanchong.blog.Service.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoggerServiceImpl implements LoggerService {

    private final LoggerRepository loggerRepository;

    @Autowired
    public LoggerServiceImpl(LoggerRepository loggerRepository) {
        this.loggerRepository = loggerRepository;
    }

    @Override
    public List<Logger> getLoggerList() {
        return loggerRepository.findAll();
    }

    @Override
    public Page<Logger> getPageLoggerList(Integer page) {
        Sort sort= new Sort(Sort.Direction.DESC, "sort");
        PageRequest pageRequest = PageRequest.of(page-1,10);
        return loggerRepository.findAll(pageRequest);
    }


    @Override
    public Logger getLoggerById(Long id) {
        return loggerRepository.findById(id).get();
    }

    @Override
    public Logger addLogger(Logger logger) {
        return loggerRepository.save(logger);
    }

    @Override
    public Logger updateLogger(Logger logger) {
        return loggerRepository.save(logger);
    }
}
