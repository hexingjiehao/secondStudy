package com.xiongjie.mapper;

import com.xiongjie.entity.mybatis.UserMybatisXml;
import org.springframework.stereotype.Component;

/**
 * Created by xiongjie on 2018/10/22.
 */
@Component
public interface UserMybatisXmlMapper {

    UserMybatisXml getOne(Long id);

}
