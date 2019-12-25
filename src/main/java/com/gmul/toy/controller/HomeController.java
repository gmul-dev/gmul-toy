package com.gmul.toy.controller;

import com.gmul.toy.domain.posts.PostsRepository;
import com.gmul.toy.domain.posts.dto.PostsSaveRequestDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private PostsRepository postsRepository;

    public HomeController(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @GetMapping("/hello")
    public String home(){
        return "HelloWorld";
    }

    @PostMapping("/posts")
    public void savePosts(@RequestBody PostsSaveRequestDto post){
        postsRepository.save(post.toEntity());
    }
}
