package com.xiongjie.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by xiongjie on 2018/10/23.
 */
@Component
@Order(1)
public class OrderRunner1 implements CommandLineRunner {

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("The OrderRunner1 start to initialize ...");
    }
}
