package com.cw98.spring02.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.cw98.spring02.mapper"})
public class MyBatisConfig {
    
}
