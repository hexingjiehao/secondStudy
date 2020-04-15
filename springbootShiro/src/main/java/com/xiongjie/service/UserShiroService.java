package com.xiongjie.service;

import com.xiongjie.dao.UserShiroJpa;
import com.xiongjie.entity.UserShiro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xiongjie on 2018/10/23.
 */
@Service
public class UserShiroService {

    @Autowired
    private UserShiroJpa userShiroJpa;

    public UserShiro findByUsername(String username){
        UserShiro userShiro=userShiroJpa.findByUsername(username);
        return userShiro;
    }

}
