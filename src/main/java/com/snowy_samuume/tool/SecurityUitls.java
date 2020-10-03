package com.snowy_samuume.tool;

import com.snowy_samuume.config.auth.jwt.SpringBeanFactoryUtils;
import com.snowy_samuume.entity.User;
import com.snowy_samuume.service.impl.UserServiceImpl;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Random;

/**
 * @author snowy
 * @date 2020/10/1 15:39
 */
@UtilityClass
public class SecurityUitls {

    public User getUserInfo(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserServiceImpl userService = SpringBeanFactoryUtils.getBean(UserServiceImpl.class);
        User user = (User)userService.loadUserByUsername(String.valueOf(principal));
        return  user;
    }

    /**
     * 产生验证码
     * */
    public  String verificationCode(){
        Random random = new Random();
        int i = random.nextInt(1000000);
        return String.valueOf(i);
    }
}
