package com.snowy_samuume.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.snowy_samuume.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author snowy
 * @date 2020/9/19 21:19
 */
public interface UserMapper extends BaseMapper<User> {

    int updateUserOfPassword(@Param("username") String username, @Param("newPassword") String newPassword);
    
}
