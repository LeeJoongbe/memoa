package com.example.memopro.repository;

import com.example.memopro.entity.Memo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    //기존 dao, mapper과 같은 역활을 한다.
    //단 JpaRepository를 상속함에 따라 jpa를 사용해서 db에 접근가능하다.
    //jpa java 표준 orm기술이다.
    // JpaRepository<사용할 class타입, pk타입>
    //findbyId(id) 찾기 id로 찾기 읽기getOne     //select
    //findbyAll 찾기 전부 찾기               //select
    //save(entity) 저장                   //insert
    //save(entity) 수정                   //update
    //deleteById(id) 삭제                     //delete

    //페이징 / 정렬 처리하기
    //jpa에서는 페이징 처리를 pageable을 사용하여 페이징 처리를 합니다.
    //sql 코드는 기존 limit를 사용합니다.

    //Query Methods

    Memo  findByMno (Long a);

    Memo findByMemoText (String memoText);

    //select * from memo where memo_text like
    // concat('%', #{memoText}, '%')
    List<Memo> findByMemoTextContains(String memoText);
    //select * from memo where memo_text like
    // concat('%', #{memoText}, '%')
    // or mno = #{mno}
    List<Memo> findByMemoTextContainsOrMno(String memoText, Long mno);

    //select * from meno
    // where mno between a and b
    // order by mno desc
    List<Memo> findByMnoBetweenOrderByMnoDesc(Long a, Long b);
    //select * from tbl_memo  where mno between a and b
    //    order by m1_0.mno desc
    //    limit ?, ?    //0페이지 , 10개씩
    List<Memo> findByMnoBetween(Long a, Long b, Pageable pageable);
    //검색조건은 MemoText에서 a가 들어가는 로우중에 mno가 b에서부터 c 인거
    List<Memo> findByMemoTextContainsAndMnoBetween(String a, int b, int c);

}
