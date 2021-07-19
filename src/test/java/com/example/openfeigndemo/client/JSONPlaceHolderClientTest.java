package com.example.openfeigndemo.client;

import com.example.openfeigndemo.model.Post;
import com.google.gson.Gson;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.StaticServerList;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        classes = {
                JSONPlaceHolderClientTest.FakeFeignConfiguration.class,
                JSONPlaceHolderClientTest.FakeBooksRestService.class
        },
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class JSONPlaceHolderClientTest {

    @Autowired
    private JSONPlaceHolderClient jsonPlaceHolderClient;

    @Test
    @DisplayName(value = "Get Posts from JSONPlaceHolder Feign client")
    void getPosts() throws FileNotFoundException {
        List<Post> posts = jsonPlaceHolderClient.getPosts();
        assertThat(posts).isEqualTo(getPostsFromJSONFile());
    }

    @RestController
    @RequestMapping(path = "/posts")
    static class FakeBooksRestService {

        @GetMapping(produces = "application/json")
        public String getMessage() throws FileNotFoundException {
            return new Gson().toJson(getPostsFromJSONFile());
        }
    }

    @Configuration(proxyBeanMethods = false)
    static class FakeRibbonConfiguration {

        @LocalServerPort
        int port;

        @Bean
        public ServerList<Server> serverList() {
            return new StaticServerList<>(new Server("localhost", port));
        }
    }

    @Configuration(proxyBeanMethods = false)
    @EnableFeignClients(clients = JSONPlaceHolderClient.class)
    @EnableAutoConfiguration
    @RibbonClient(
            name = "jsonplaceholder",
            configuration = JSONPlaceHolderClientTest.FakeRibbonConfiguration.class)
    static class FakeFeignConfiguration {}

    private static List<Post> getPostsFromJSONFile() throws FileNotFoundException {
        Gson gson = new Gson();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/test/resources/data/data.json"));
        return Arrays.asList(gson.fromJson(bufferedReader, Post[].class));
    }
}