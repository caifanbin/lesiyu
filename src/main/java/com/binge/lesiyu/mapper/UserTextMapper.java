package com.binge.lesiyu.mapper;


import com.binge.lesiyu.bean.UserText;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserTextMapper {

    @Insert("insert into usertext(userid,text,username,time,title) values(#{userid},#{text},#{username},#{time},#{title})")
    void saveText(UserText userText);

    @Select("select * from usertext where id=#{id}")
    UserText getText(long id);

    @Select("select * from usertext")
    List<UserText> getAll();

}
