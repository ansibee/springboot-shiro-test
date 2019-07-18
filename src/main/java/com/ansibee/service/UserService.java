package com.ansibee.service;

import com.ansibee.domain.UserInfo;

public interface UserService {

    UserInfo findByName(String name);

    UserInfo findById(Integer id);
}
