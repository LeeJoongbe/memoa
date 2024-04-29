package com.example.memopro.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity //엔티티임을 명시하고 데이터베이스의 테이블과 같은 구조로 만들어집니다.
        //엔티티를 사용하고 properties설정하면, 테이블이 생성됩니다.
@Table(name = "tbl_memo")       //테이블명은 tbl_memo로 만든다
@ToString
@Getter
@Builder
//builder 이용하기위해서 @AllArgsConstructor ,
// @NoArgsConstructor 필요 컴파일 에러 X
@AllArgsConstructor
@NoArgsConstructor
public class Memo {

    @Id     //table의 pk를 매핑함
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //자동증감값 지정 auto_increment
    private Long mno;

    @Column(length = 200, nullable = false)
    //길이는 200, not null
    private String memoText;

    private String memo;


}
