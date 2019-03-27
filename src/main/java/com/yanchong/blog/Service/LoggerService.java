package com.yanchong.blog.Service;

import com.yanchong.blog.Entity.Logger;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LoggerService {
    List<Logger> getLoggerList();

    Page<Logger> getPageLoggerList(Integer page);

    Logger getLoggerById(Long id);

    Logger addLogger(Logger logger);

    Logger updateLogger(Logger logger);
}
