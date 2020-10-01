package com.snowy_samuume.tool;

import com.snowy_samuume.entity.Roles;
import com.snowy_samuume.entity.User;
import com.snowy_samuume.entity.VO.UserVO;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author snowy
 * @date 2020/10/1 15:39
 */
@UtilityClass
public class SecurityUitls {

    public UserVO getUserInfo(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Object o = RedisBean.redis.boundValueOps("current:user:" + principal.toString()).get();
        UserVO user =JsonUtils.toJavaObject(o, UserVO.class);
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
    /**
     * 保存用户信息到redis
     * */
    public void storeUserInfo(User user, Roles roles, List<String> authorities){
        UserVO userVO = UserVO.getInstance(user, roles, authorities);
        boolean present = Optional.ofNullable(RedisBean.redis.boundValueOps("current:user:" + userVO.getUsername()).get()).isPresent();
        if (!present){
            RedisBean.redis.boundValueOps("current:user:"+userVO.getUsername()).set(userVO,1, TimeUnit.HOURS);
        }
    }
}
