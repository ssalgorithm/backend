package com.ssafy.ssalgorithm.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.ssafy.ssalgorithm.**.dao")
public class MyBatisConfig {
}
