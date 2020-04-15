package com.xiongjie.fallback;

import com.xiongjie.call.HelloRemote;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by xiongjie on 2018/10/24.
 */
@Component
public class HelloRemoteHystrix implements HelloRemote {

    @Override
    public String index(@RequestParam(value = "name")String name) {
        return "hello" +name+", this messge send failed ";
    }
}
