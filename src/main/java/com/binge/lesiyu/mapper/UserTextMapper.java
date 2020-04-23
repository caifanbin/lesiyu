package com.binge.lesiyu.mapper;


import com.binge.lesiyu.bean.UserText;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserTextMapper {

    @Insert("insert into usertext(userid,text,username,time,title,preface) values(#{userid},#{text},#{username},#{time},#{title},#{preface})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void saveText(UserText userText);

    @Select("select id,userid,text,username,time,title,preface from usertext where id=#{id}")
    UserText getText(long id);

    @Select("select id,userid,text,username,time,title,preface from usertext")
    List<UserText> getAll();

}
