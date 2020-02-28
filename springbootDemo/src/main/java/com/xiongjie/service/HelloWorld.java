package com.xiongjie.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by xiongjie on 2018/10/22.
 */
@Component
public class HelloWorld {

    public void sayHelloWorld(){
        System.out.println("hello world!");
    }

}
