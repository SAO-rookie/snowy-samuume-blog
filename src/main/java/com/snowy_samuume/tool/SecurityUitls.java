package com.snowy_samuume.tool;

import com.snowy_samuume.entity.VO.UserVO;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author snowy
 * @date 2020/10/1 15:39
 */
@UtilityClass
public class SecurityUitls {
    public UserVO getUserInfo(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Object o = RedisBean.redis.opsForValue().get("current:user:" + principal.toString());
        UserVO user =JsonUtils.toJavaObject(o, UserVO.class);
        return  user;
    }
}
