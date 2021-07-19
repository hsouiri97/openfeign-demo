package com.example.openfeigndemo.client;

import com.example.openfeigndemo.model.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "jsonplaceholder", url = "${open-feign.url}")
public interface JSONPlaceHolderClient {

    @RequestMapping(value = "posts", method = RequestMethod.GET)
    List<Post> getPosts();
}
