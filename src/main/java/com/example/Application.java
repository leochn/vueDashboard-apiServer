package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.utils.SpringContextUtil;


@EnableWebMvc
@SpringBootApplication
@MapperScan(basePackages = "com.example.mapper")
public class Application {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
		SpringContextUtil.setApplicationContext(applicationContext);
	}
}
