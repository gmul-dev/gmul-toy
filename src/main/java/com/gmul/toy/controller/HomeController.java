package com.gmul.toy.controller;

import com.gmul.toy.domain.posts.Posts;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/hello")
    public String home(){
        return "HelloWorld";
    }
}
