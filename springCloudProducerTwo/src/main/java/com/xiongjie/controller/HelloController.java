package com.xiongjie.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xiongjie on 2018/10/24.
 */
@RestController
public class HelloController {

    @RequestMapping(value="/hello")
    public String index(@RequestParam("name") String name){
        return "hello "+name+"，this is two messge";
    }

}
