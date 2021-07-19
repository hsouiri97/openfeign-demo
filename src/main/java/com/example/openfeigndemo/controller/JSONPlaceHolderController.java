package com.example.openfeigndemo.controller;

import com.example.openfeigndemo.model.Book;
import com.example.openfeigndemo.service.JSONPlaceHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class JSONPlaceHolderController {

    private final JSONPlaceHolderService jsonPlaceHolderService;

    @Autowired
    public JSONPlaceHolderController(JSONPlaceHolderService jsonPlaceHolderService) {
        this.jsonPlaceHolderService = jsonPlaceHolderService;
    }


    @GetMapping("books")
    List<Book> getPosts() {
        return jsonPlaceHolderService.getBooks();
    }
}
