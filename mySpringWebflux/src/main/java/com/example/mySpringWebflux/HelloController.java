package com.example.mySpringWebflux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @version 1.0
 * @program: mySpringWebflux
 * @packageName: com.example.mySpringWebflux
 * @className HelloController
 * @description: TODO
 * @author: xj
 * @create: 2021-01-16 14:11:28
 **/
@RestController
public class HelloController {

    @GetMapping("/hello")
    public Mono<String> hello() {
        return Mono.just("Welcome to reactive world ~");
    }
}
