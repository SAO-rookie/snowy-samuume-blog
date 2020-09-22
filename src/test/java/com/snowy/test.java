package com.snowy;

import com.snowy_samuume.BlogApplication;
import com.snowy_samuume.entity.User;
import com.snowy_samuume.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author snowy
 * @date 2020/9/22 21:58
 */
@SpringBootTest(classes = BlogApplication.class)
@RunWith(SpringRunner.class)
public class test {
    @Autowired
    private UserServiceImpl userService;

    @Test
    public void get(){
        UserDetails admin = userService.loadUserByUsername("admin");
        System.out.println(admin.getAuthorities());
    }
}
