package com.xiongjie.dao;

import com.xiongjie.entity.UserShiro;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by xiongjie on 2018/10/23.
 */
public interface UserShiroJpa extends JpaRepository<UserShiro,Long>{

    /**通过username查找用户信息;*/
    public UserShiro findByUsername(String username);

}
