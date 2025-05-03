package com.jaeyong.todolist.domain;

import jakarta.persistence.*;
import lombok.*;

/*
    ser 엔티티 클래스
    DB의 user 테이블과 매핑될 객체
 */
@Entity                 // 이 클래스는 JPA에서 관리되는 엔티티를 표시
@Table(name = "users")
@Getter                 //Getter 자동생성
@Setter                 //Setter 자동생성
@NoArgsConstructor      //기본 생성자 생성
@AllArgsConstructor     // 모든 필드를 받는 생성자 생성
@Builder                // 빌더 패턴 지원

public class User {

    @Id //테이블의 기본키(PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // MySQL의 AUTO_INCREMENT처럼 DB가 자동으로 ID를 증가시킴

    private Long id;        // 사용자 아이디
    private String email;   // 사용자 이메일
    private String password;// 사용자 비밀번호
    private String nickname;// 사용자 닉네임

}
