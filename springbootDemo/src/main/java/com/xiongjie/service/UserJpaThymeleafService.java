package com.xiongjie.service;

import com.xiongjie.entity.UserMysqlJpa;
import com.xiongjie.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiongjie on 2018/10/23.
 */
@Service
public class UserJpaThymeleafService {

    @Autowired
    private UserRepository userRepository;

    public List<UserMysqlJpa> list(){
        return userRepository.findAll();
    }


}
