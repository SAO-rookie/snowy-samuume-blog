package com.snowy_samuume.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snowy_samuume.entity.Permission;
import com.snowy_samuume.entity.Roles;
import com.snowy_samuume.entity.User;
import com.snowy_samuume.mapper.UserMapper;
import com.snowy_samuume.service.PermissionService;
import com.snowy_samuume.service.RolesService;
import com.snowy_samuume.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author snowy
 * @since 2020-09-19
 */
@Service
@CacheConfig(cacheNames ="user_")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RolesService rolesService;
    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    @Cacheable(key ="#p0+'*'")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));
        // 判断用户是否空 为空抛出异常
        Optional.ofNullable(user).orElseThrow(()-> new UsernameNotFoundException("用户名不存在"));
        // 获取当前角色
        Roles role = rolesService.getById(user.getRolesId());
        // 根据当前角色搜索权限
        List<String> permissionCodes = permissionService.getListByRolesId(role.getId()).stream()
                .map(Permission::getPermissionCode)
                .collect(Collectors.toList());
        permissionCodes.add(role.getRoleCode());
        // 将权限给予用户对象
        user.setAuthorities( AuthorityUtils.commaSeparatedStringToAuthorityList(
                String.join(",",permissionCodes)
        ));
        return user;
    }

    @Override
    public boolean saveUser(User user) {
        user.setRolesId(1);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.insert(user)>0;
    }
}
