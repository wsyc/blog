package com.yanchong.blog.Respository;

import com.yanchong.blog.Entity.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LoggerRepository extends JpaRepository<Logger,Long>, JpaSpecificationExecutor<Logger> {
}
