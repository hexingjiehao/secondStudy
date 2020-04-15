package com.xiongjie;

import com.xiongjie.service.HelloWorld;
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
public class HelloWorldTests {

    @Autowired
    private HelloWorld helloWorld;

    @Test
    public void sayHelloWorldTest(){
        helloWorld.sayHelloWorld();
    }

}
