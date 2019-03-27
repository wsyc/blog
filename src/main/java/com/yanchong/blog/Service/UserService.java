package com.yanchong.blog.Service;

import com.yanchong.blog.Entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    List<User> getUserList();
    User getUserById(Long id);
    User addUserInfo(User user);
    User updateUserInfoById(User user);
    void deleteUserInfoById(Long Id);
    List<User>getCurrentUserList();
    Page<User> getPageUserList();
    User getUserByUsername(String userName);
}
