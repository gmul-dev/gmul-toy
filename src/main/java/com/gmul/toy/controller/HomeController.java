package com.gmul.toy.controller;

import com.gmul.toy.dao.MemberRepository;
import com.gmul.toy.dao.PostsRepository;
import com.gmul.toy.domain.posts.dto.PostsSaveRequestDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final PostsRepository postsRepository;
    private final MemberRepository memberRepository;

    public HomeController(PostsRepository postsRepository, MemberRepository memberRepository) {
        this.postsRepository = postsRepository;
        this.memberRepository = memberRepository;
    }

    @GetMapping("/hello")
    public String home(){
        return "HelloWorld";
    }

    @PostMapping("/posts")
    public void savePosts(@RequestBody PostsSaveRequestDto post){
        postsRepository.save(post.toEntity());
    }

    @GetMapping("/member")
    public String getMember(){
        return String.valueOf(memberRepository.findById(1L));
    }

}
