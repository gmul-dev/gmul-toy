package com.gmul.toy.domain.exampledb;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
    private String memberId;

    @Column(name = "member_pwd")
    private String memberPwd;

    @Column(name = "member_nickname")
    private String memberNickname;

    @Column(name = "reg_date")
    @CreatedDate
    private LocalDateTime regDate;

    @Column(name = "mod_date")
    @LastModifiedDate
    private LocalDateTime modData;

    @Builder
    public Member(long memberNo, String memberId, String memberPwd, String memberNickname){
        this.memberNo = memberNo;
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.memberNickname = memberNickname;
    }
}
