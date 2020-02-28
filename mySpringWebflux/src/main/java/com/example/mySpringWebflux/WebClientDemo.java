package com.example.mySpringWebflux;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Base64Utils;
import org.springframework.web.reactive.function.client.WebClient;

import static java.nio.charset.StandardCharsets.UTF_8;

public class WebClientDemo {

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
