package com.ansibee.domain;

import lombok.Data;

@Data
public class UserInfo {

    private Integer userId;
    private String userName;
    private String password;
    private String perms;

}

