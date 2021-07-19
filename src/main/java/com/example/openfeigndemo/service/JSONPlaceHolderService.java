package com.example.openfeigndemo.service;

import com.example.openfeigndemo.client.JSONPlaceHolderClient;
import com.example.openfeigndemo.model.Book;
import com.example.openfeigndemo.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JSONPlaceHolderService {

    private final JSONPlaceHolderClient jsonPlaceHolderClient;

    public JSONPlaceHolderService(JSONPlaceHolderClient jsonPlaceHolderClient) {
        this.jsonPlaceHolderClient = jsonPlaceHolderClient;
    }

    public List<Book> getBooks() {
        List<Post> posts = jsonPlaceHolderClient.getPosts();
        return posts.stream().map(post -> new Book(post.getTitle(), post.getBody())).collect(Collectors.toList());
    }
}
