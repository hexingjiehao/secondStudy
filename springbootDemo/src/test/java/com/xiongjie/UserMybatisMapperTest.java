//package com.xiongjie;
//
//import com.xiongjie.entity.mybatis.UserMybatis;
//import com.xiongjie.mapper.UserMybatisMapper;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.annotation.Resource;
//import java.util.List;
//
///**
// * Created by xiongjie on 2018/10/22.
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class UserMybatisMapperTest {
//
//    @Autowired
//    private UserMybatisMapper  userMybatisMapper;
//
//    @Test
//    public void testAllAnnotationMybatis(){
//
//        if(userMybatisMapper==null){
//            System.out.println("userMybatis is null");
//            return ;
//        }
//
//        userMybatisMapper.insert(new UserMybatis("aa", "a123456"));
//        userMybatisMapper.insert(new UserMybatis("bb", "b123456"));
//        userMybatisMapper.insert(new UserMybatis("cc", "b123456"));
//
//        Assert.assertEquals(3, userMybatisMapper.getAll().size());
//
//        List<UserMybatis> users = userMybatisMapper.getAll();
//        System.out.println(users.toString());
//
//        UserMybatis templates.user = userMybatisMapper.getOne(3l);
//        System.out.println(templates.user.toString());
//        templates.user.setPassWord("admin");
//        userMybatisMapper.update(templates.user);
//        Assert.assertTrue(("admin".equals(userMybatisMapper.getOne(3l).getPassWord())));
//
//        userMybatisMapper.delete(3l);
//        Assert.assertEquals(2,userMybatisMapper.getAll().size());
//    }
//
//
//
//}
