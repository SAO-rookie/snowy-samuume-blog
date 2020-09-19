package com.snowy_samuume;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author snowy
 * @date 2020/8/9 0:10
 */
@SpringBootApplication
@EnableSwagger2
@MapperScan(basePackages = {"com.snowy_samuume.mapper"})
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class,args);
    }
}
