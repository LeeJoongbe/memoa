package com.example.memopro.repositoryTest;


import com.example.memopro.entity.Memo;
import com.example.memopro.repository.MemoRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MemoRepositoryTest {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testClass(){
        log.info(memoRepository.getClass().getName());
    }

    @Test
    public void testInsert(){
        IntStream.rangeClosed(1, 100).forEach(value ->
                {
                    Memo memo = Memo.builder()
                            .memoText("sample" + value)
                            .build();

                    memoRepository.save(memo);
                    //repository에서 save는 기본적으로 entity타입을 가져온다.
                    // repository를 만들때
                    // JpaRepository<사용할 class타입 이거이거이거이거, pk타입>

                });

    }

    @Test
    public void testSelect(){
        Long mno = 13L;

        Optional<Memo> memo = memoRepository.findById(mno);

        log.info(memo.orElseThrow()); // orElseThrow 값이 없다면 에러
        //log.info(memo.get());
        log.info("출력출력");

    }

    @Test
    public void testUpdate(){

        Memo memo = new Memo(11L, "수정수정김수정");

        log.info(memoRepository.save(memo));

    }

    @Test
    public void testDelete(){

        memoRepository.deleteById(11L);
        memoRepository.deleteById(10L);

    }
    @Test
    public void testAll(){

         List<Memo> memoList =  memoRepository.findAll(); //전체 검색 반환타입
                                    //내가 만든 반환타입 List형태

        memoList.forEach(memo -> log.info(memo));

    }

    @Test
    public void testPaging(){

        //1페이지 10개씩
        //페이징 처리만
        //Pageable pageable = PageRequest.of(1, 5);
        //정렬조건 추가
        Sort sort = Sort.by("mno").descending();
        Pageable pageable = PageRequest.of(1, 5, sort);

        Page<Memo> memoPage =  memoRepository.findAll(pageable);

        memoPage.forEach(memo -> log.info(memo));

        log.info("총 페이지 : " + memoPage.getTotalPages()); //총 몇페이지인가?
        log.info("전체 row수 : " + memoPage.getTotalElements());  //전체 개수 row
        log.info("현재페이지 : " + memoPage.getNumber());         //현재 페이지 번호
        log.info("한페이지 출력수 : " + memoPage.getSize());           //현재 페이지의 크기 몇개씩 보여주는지
        log.info("다음페이지 존재 여부 : " + memoPage.hasNext());           //다음 페이지 존재 여부
        log.info("시작페이지 이니? : " + memoPage.isFirst());           //시작페이지 인지 여부
    }


    @Test
    public void testfindByMno(){

        Memo memo = memoRepository.findByMno(3L);
        log.info(memo);

        log.info(memoRepository.findByMemoText("sample3"));


        log.info(memoRepository.findByMemoTextContains("e3"));

        log.info(memoRepository
                .findByMemoTextContainsOrMno
                        ("e1", 99L));

        log.info(memoRepository
                .findByMnoBetweenOrderByMnoDesc
                        (3L, 10L));

    }

    @Test
    public void searchtest(){

        //1페이지 10개씩
        //페이징 처리만
        //Pageable pageable = PageRequest.of(1, 5);
        //정렬조건 추가
//        Sort sort = Sort.by("mno").descending();
//        Pageable pageable = PageRequest.of(1, 5, sort);

        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());

        List<Memo> memoList =    memoRepository.findByMnoBetween(34L, 87L, pageable);

        memoList.forEach(memo -> log.info(memo));



    }



















}
