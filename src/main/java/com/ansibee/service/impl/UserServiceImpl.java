package com.ansibee.service.impl;

import com.ansibee.domain.UserInfo;
import com.ansibee.mapper.UserMapper;
import com.ansibee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserInfo findByName(String name) {
        return userMapper.findByName(name);
    }

    @Override
    public UserInfo findById(Integer id) {
        return userMapper.findById(id);
    }
}
