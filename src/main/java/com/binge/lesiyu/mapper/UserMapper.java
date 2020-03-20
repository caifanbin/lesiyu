package com.binge.lesiyu.mapper;


import com.binge.lesiyu.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user where username=#{username}")
    User getUserByUsername(String username);
}
