package com.xiongjie.mapper;

import com.xiongjie.entity.mybatis.UserMybatis;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xiongjie on 2018/10/22.
 */
@Component
public interface UserMybatisMapper {

    @Select("SELECT * FROM userMybatis")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userName",  column = "user_name"),
            @Result(property = "passWord", column = "pass_word")
    })
    List<UserMybatis> getAll();

    @Select("SELECT * FROM userMybatis WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userName",  column = "user_name"),
            @Result(property = "passWord", column = "pass_word")
    })
    UserMybatis getOne(Long id);

    @Insert("INSERT INTO userMybatis(user_name,pass_word) VALUES(#{userName}, #{passWord})")
    void insert(UserMybatis user);

    @Update("UPDATE userMybatis SET user_name=#{userName},pass_word=#{passWord} WHERE id =#{id}")
    void update(UserMybatis user);

    @Delete("DELETE FROM userMybatis WHERE id =#{id}")
    void delete(Long id);

}
