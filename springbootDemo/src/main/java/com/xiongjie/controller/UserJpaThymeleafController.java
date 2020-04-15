package com.xiongjie.controller;

import com.xiongjie.entity.UserMysqlJpa;
import com.xiongjie.service.UserJpaThymeleafService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by xiongjie on 2018/10/23.
 */
@Controller
public class UserJpaThymeleafController {

    @Autowired
    private UserJpaThymeleafService userJpaThymeleafService;

    @RequestMapping(value="/",method = RequestMethod.GET)
    public String index(){
        return "redirect:list";
    }

    /**
     * @param model 上下文对象
     * @return
     */
    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String list(Model model){
        List<UserMysqlJpa> users=userJpaThymeleafService.list();
        model.addAttribute("users", users);
        //跳转到制定的模板页面
        return "user/list";
    }

}
