package com.gmul.toy.domain.exampledb;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private long memberNo;

    @Column(name = "member_id")
    private String username;

    @Column(name = "member_pwd")
    private String password;

    @Column(name = "member_nickname")
    private String memberNickname;

    @Column(name = "reg_date")
    @CreatedDate
    private LocalDateTime regDate;

    @Column(name = "mod_date")
    @LastModifiedDate
    private LocalDateTime modDate;

    @Builder
    public Member(long memberNo, String username, String password, String memberNickname){
        this.memberNo = memberNo;
        this.username = username;
        this.password = password;
        this.memberNickname = memberNickname;
    }
}
