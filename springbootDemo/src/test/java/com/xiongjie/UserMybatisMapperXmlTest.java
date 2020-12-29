package com.xiongjie;

import com.xiongjie.mapper.UserMybatisXmlMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by xiongjie on 2018/10/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMybatisMapperXmlTest {

    @Autowired
    private UserMybatisXmlMapper userMybatisXmlMapper;

    @Test
    public void testXmlMybatis(){

        if(userMybatisXmlMapper==null){
            System.out.println("userMybatisXmlMapper is null");
            return ;
        }

        Assert.assertEquals("bb",userMybatisXmlMapper.getOne(2l).getUserName() );
    }

}
