package com.sgmw.sales.app.sample.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan("com.sgmw.sales.app.sample.repository.mybatis")
@EnableTransactionManagement
public class MybatisConfigruation {

}
