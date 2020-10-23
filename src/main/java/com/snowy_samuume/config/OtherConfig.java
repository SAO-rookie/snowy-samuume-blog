package com.snowy_samuume.config;

import com.snowy_samuume.tool.IdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: snowy-samuume-blog
 * @description: 其他的配置
 * @author: snowy
 * @create: 2020-10-23 10:20
 **/
@Configuration
public class OtherConfig {
    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }
}
