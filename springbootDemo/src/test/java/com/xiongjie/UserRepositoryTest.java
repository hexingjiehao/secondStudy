//package com.xiongjie;
//
//import com.xiongjie.entity.UserMysqlJpa;
//import com.xiongjie.dao.UserRepository;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
///**
// * Created by xiongjie on 2018/10/22.
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class UserRepositoryTest {
//
//    //不需要在接口注解,因为继承了jpa的实现
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    public void testSpringbootJpaMysql(){
//
//        if(userRepository==null){
//            System.out.println("userRepository等于null");
//            return ;
//        }
//
//        userRepository.save(new UserMysqlJpa("xiongjie","xj","xj@qq.com") );
//        userRepository.save(new UserMysqlJpa("hexingjie","hxj","hxj@163.com") );
//        userRepository.save(new UserMysqlJpa("huaidan","hd","hd@qq.com") );
//
//        //断言，比较两个值是否一致
//        Assert.assertEquals(3   , userRepository.findAll().size());
//        Assert.assertEquals(null, userRepository.findByUserNameOrPassWord("hexingjie", "hxj").getMail());
//        userRepository.delete(userRepository.findByUserName("huaidan"));
//
//    }
//
//
//}
