package com.example.mySpringWebflux;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.Base64Utils;
import org.springframework.web.reactive.function.client.WebClient;

import static java.nio.charset.StandardCharsets.UTF_8;

@RunWith(SpringRunner.class)
@WebFluxTest(controllers = HelloController.class)
public class WebClientDemo {

    @Autowired
    WebTestClient client;

    @Test
    public void getHello() {
        client.get().uri("/hello").exchange().expectStatus().isOk();
    }


    @Test
    public void test(){

        WebClient webClient = WebClient.builder()
                .baseUrl("https://api.github.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/vnd.github.v3+json")
                .defaultHeader(HttpHeaders.USER_AGENT, "Spring 5 WebClient")
                .build();

        webClient.get()
                .uri("/user/repos")
                .header("Authorization", "Basic " + Base64Utils.encodeToString(("hexingjiehao: fd11cf480f62daa848c328d88450e6b15a60bb72 ").getBytes(UTF_8)))
                .retrieve();

    }

}
