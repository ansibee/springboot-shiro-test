package com.ansibee.mapper;

import com.ansibee.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user_info where user_name = #{userName}")
    UserInfo findByName(String name);

    @Select("select * from user_info where user_id = #{userId}")
    UserInfo findById(Integer id);
}
