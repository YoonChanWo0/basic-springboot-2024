package com.cw98.backboard.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
     @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long rno;

    @Column(name = "content", length = 1000)
    private String content;

    @CreatedBy
    @Column(name="createDate", updatable=false)
    private LocalDateTime createDate;

    // 중요, ERD러 DB를 설계하지 않고 엔티티 클래스로 관계를 형성하려면 반드시 사용
    @ManyToOne
    private Board board;
}