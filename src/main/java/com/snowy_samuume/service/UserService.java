package com.snowy_samuume.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snowy_samuume.entity.User;
import com.snowy_samuume.entity.VO.UserVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author snowy
 * @since 2020-09-19
 */
public interface UserService extends IService<User> {
   boolean saveUser(UserVO userVO);

   boolean sendVerificationCode(String email);

   UserVO getUserInfoById(Integer userId);

}
