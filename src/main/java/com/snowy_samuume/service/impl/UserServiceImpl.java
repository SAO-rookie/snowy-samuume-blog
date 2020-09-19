package com.snowy_samuume.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snowy_samuume.entity.User;
import com.snowy_samuume.mapper.UserMapper;
import com.snowy_samuume.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author snowy
 * @since 2020-09-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
