package com.example.springbootswagger.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @ApiOperation(value="测试Swagger的使用", notes="使用OpenAPI规范")
    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String testAPIDemo (){

        System.out.println("测试Swagger使用");

        return "OK";
    }


}
