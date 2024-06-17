package com.cw98.backboard.repository;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cw98.backboard.entity.Board;

@SpringBootTest

public class BoardRepositoryTests {
    // JUnit 테스트
    @Autowired
    private BoardRepository boardRepository;



    @Test
    void testInsertBoard(){
        Board board1 = new Board(); // 전통적인 객체 생성 방식  
        board1.setTitle("첫번쨰 테스트입니다.");
        board1.setContent("어쩌라구!");
        board1.setCreateDate(LocalDateTime.now());
        this.boardRepository.save(board1);
        // Builder를 사용한 객체 생성방식
        Board board2 = Board.builder().title("두번째 테스트입니다.")
                            .content("그래서 어쩌라구")
                            .createDate(LocalDateTime.now()).build();
        this.boardRepository.save(board2);
        System.out.println("board 테스트 완료!!");
       
    }
}
