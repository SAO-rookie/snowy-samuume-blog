package com.snowy;

import com.snowy_samuume.BlogApplication;
import com.snowy_samuume.entity.User;
import com.snowy_samuume.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

/**
 * @author snowy
 * @date 2020/9/22 21:58
 */
@SpringBootTest(classes = BlogApplication.class)
@RunWith(SpringRunner.class)
public class test {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void get(){

        /*User user = new User();
        user.setNickname("dasda");
        user.setPassword("dsda");
        redisTemplate.opsForValue().set("user",user);*/
        System.out.println(redisTemplate.boundValueOps("user::snowy:username").get());
    }
}
