package 认证授权;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.request.AuthBaiduRequest;
import me.zhyd.oauth.request.AuthGiteeRequest;
import me.zhyd.oauth.request.AuthRequest;

/**
 * @version 1.0
 * @program: hxj
 * @packageName: 认证授权
 * @className Study_JustAuth
 * @author: xj
 * @create: 2020-08-25 16:51:16
 **/
public class Study_JustAuth {

    public static void main(String[] args) {
        // 创建授权request
        AuthRequest authRequest = new AuthBaiduRequest(AuthConfig.builder()
                .clientId("18351807162")
                .clientSecret("xj15928286403")
                .redirectUri("http://www.baidu.com")
                .build());
        System.out.println(authRequest.authorize("state"));
    }

}
