package com.cw98.backboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cw98.backboard.entity.Reset;
import java.util.Optional;

public interface ResetRepository extends JpaRepository<Reset, Integer>{
    
    Optional<Reset> findByUuid(String uuid);
}
