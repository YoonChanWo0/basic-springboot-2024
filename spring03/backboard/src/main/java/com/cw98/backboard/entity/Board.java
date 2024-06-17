package com.cw98.backboard.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

// 게시판 보드 테이블 엔티티
@Entity
@Getter
@Setter
@Builder // 객체 생성을 간략화
@NoArgsConstructor // 파라미터 없는 기본생성자 자동생성
@AllArgsConstructor // 맴버변수 모두를 파라미터로 가지는 생성자 자동생성
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long bno; // pk

    @Column(name = "title", length = 250)
    private String title; // 글제목

    @Column(name = "content", length = 4000)
    private String content; // 글내용

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate; // 글 생성일

    // 중요, Relationship 일대다
    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Reply> replyList;

}
