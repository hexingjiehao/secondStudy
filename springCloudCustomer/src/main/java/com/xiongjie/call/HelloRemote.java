package com.xiongjie.call;

import com.xiongjie.fallback.HelloRemoteHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by xiongjie on 2018/10/24.
 */
@FeignClient(name="spring-cloud-producer",fallback = HelloRemoteHystrix.class)
public interface HelloRemote {

    //和远程服务的方法同名
    @RequestMapping(value="/hello")
    public String index(@RequestParam("name") String name);

}
