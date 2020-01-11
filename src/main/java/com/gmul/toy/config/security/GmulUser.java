package com.gmul.toy.config.security;

import com.gmul.toy.domain.exampledb.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;


public class GmulUser implements UserDetails {

    private static final long serialVersionUID = -9200327149215145003L;

    @Getter
    private Long memberNo;

    @Getter
    private String username;

    @Getter
    private String password;

    @Getter
    private String memberNickname;

    @Getter
    private LocalDateTime regDate;

    @Getter
    private LocalDateTime modDate;

    private GmulUser(Member member){
        this.memberNo = member.getMemberNo();
        this.memberNickname = member.getMemberNickname();
        this.username = member.getUsername();
        this.password = member.getPassword();
        this.regDate = member.getRegDate();
        this.modDate = member.getModDate();
    }

    public static GmulUser create(Member member) {
        return new GmulUser(member);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
