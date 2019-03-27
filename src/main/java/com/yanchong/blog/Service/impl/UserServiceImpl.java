package com.yanchong.blog.Service.impl;

import com.yanchong.blog.Entity.User;
import com.yanchong.blog.Respository.UserRepository;
import com.yanchong.blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 获取所有用户列表
     * @return List<User>
     */
    public List<User> getUserList() {
        List<User> userList=new ArrayList<User>();
        userList=userRepository.findAll();
        return  userList;
    }

    public User getUserByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }


    public User getUserById(Long id) {
        return userRepository.getOne(id);
    }

    public User addUserInfo(User user) {
        return userRepository.save(user);
    }

    public User updateUserInfoById(User user) {
        return userRepository.save(user);
    }

    public void deleteUserInfoById(Long Id) {
        userRepository.deleteById(Id);
    }

    public List<User> getCurrentUserList() {
        Sort sort=new Sort(Sort.Direction.DESC,"createTime");
        return userRepository.findAll(sort);
    }

    public Page<User> getPageUserList() {
        Sort sort=new Sort(Sort.Direction.DESC,"createTime");
        Pageable pageable;
        pageable = PageRequest.of(0,5,sort);
        return userRepository.findAll(pageable);
    }
}
