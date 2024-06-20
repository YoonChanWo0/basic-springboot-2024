package com.cw98.backboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cw98.backboard.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    
}
