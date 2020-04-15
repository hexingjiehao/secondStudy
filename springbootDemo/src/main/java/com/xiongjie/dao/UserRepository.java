package com.xiongjie.dao;

import com.xiongjie.entity.UserMysqlJpa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by xiongjie on 2018/10/22.
 */
public interface UserRepository extends JpaRepository<UserMysqlJpa, Long> {

    //这些hibernate根据字段名字段生成sql查询语句
    UserMysqlJpa findByUserName(String userName);
    UserMysqlJpa findByUserNameOrPassWord(String userName,String password);

}
