package com.snowy_samuume.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snowy_samuume.entity.User;
import com.snowy_samuume.entity.vo.UserVo;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author snowy
 * @since 2020-09-19
 */
public interface UserService extends IService<User> {
   boolean saveUser(User User);

   boolean sendVerificationCode(String email);

   User getUserInfoById(Integer userId);

   boolean updateUserById(UserVo userVo);

   boolean updateUserOfPassword(Map<String,String> map);



}
