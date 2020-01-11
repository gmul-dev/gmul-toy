package com.gmul.toy.config.security;

import com.gmul.toy.domain.exampledb.Member;
import com.gmul.toy.service.MemberService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GmulUserDatailsService implements UserDetailsService {

    private final MemberService memberService;

    public GmulUserDatailsService(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberService.getMember(username);
        if(member == null){
            throw new UsernameNotFoundException(username);
        }

        return GmulUser.create(member);
    }
}
