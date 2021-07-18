package com.example.openfeigndemo.controller;

import com.example.openfeigndemo.client.JSONPlaceHolderClient;
import com.example.openfeigndemo.model.Book;
import com.example.openfeigndemo.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/")
public class JSONPlaceHolderController {

    private final JSONPlaceHolderClient jsonPlaceHolderClient;

    @Autowired
    public JSONPlaceHolderController(JSONPlaceHolderClient jsonPlaceHolderClient) {
        this.jsonPlaceHolderClient = jsonPlaceHolderClient;
    }


    @GetMapping("posts")
    List<Book> getPosts() {
        List<Post> posts = jsonPlaceHolderClient.getPosts();
        return posts.stream().map(post -> new Book(post.getTitle(), post.getBody())).collect(Collectors.toList());
    }
}
