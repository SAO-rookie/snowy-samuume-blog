package com.snowy_samuume.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snowy_samuume.entity.Permission;
import com.snowy_samuume.entity.Roles;
import com.snowy_samuume.entity.User;
import com.snowy_samuume.entity.vo.UserVo;
import com.snowy_samuume.mapper.UserMapper;
import com.snowy_samuume.service.PermissionService;
import com.snowy_samuume.service.RolesService;
import com.snowy_samuume.service.UserService;
import com.snowy_samuume.tool.SecurityUitls;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author snowy
 * @since 2020-09-19
 */
@Slf4j
@Service
@CacheConfig(cacheNames ="user")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RolesService rolesService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private Mapper dozerBeanMapperl;

    @Value("${spring.mail.username}")
    private String fromMail;

    @Override
    @Cacheable(key ="#p0+':id'")
    public User getUserInfoById(Integer userId) {
        return userMapper.selectById(userId);
    }


    @Override
    @Cacheable(key ="#p0+':username'")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));
        // 判断用户是否空 为空抛出异常
        Optional.ofNullable(user).orElseThrow(()-> new UsernameNotFoundException("用户名不存在"));
        // 获取当前角色
        Roles role = rolesService.getById(user.getRolesId());
        // 将权限给予用户对象
        List<String> permissionCodes = permissionService.getListByRolesId(role.getId())
                .stream()
                .map(Permission::getPermissionCode)
                .collect(Collectors.toList());

        permissionCodes.add(role.getRoleCode());

        user.setAuthorities( AuthorityUtils.commaSeparatedStringToAuthorityList(
                String.join(",",permissionCodes)
        ));
        return user;
    }


    @Override
    public boolean saveUser(User user) {
        Object verificationCode = redisTemplate.opsForValue().get(user.getEmail() + ":verificationCode");
        if (user.getVerificationCode().equals(verificationCode)){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            Optional.ofNullable(user.getNickname()).ifPresent(u->user.setNickname(u));
            return userMapper.insert(user)>0;
        }
            return false;
    }

    @Override
    public boolean updateUserById(UserVo userVo) {
        User user = dozerBeanMapperl.map(userVo, User.class);
        user.setUpdateTime(DateUtil.date());
        user.setUpdateMan(SecurityUitls.getUserInfo().getId());
        return userMapper.updateById(user)>0;
    }

    @Override
    public boolean updateUserOfPassword(Map<String, String> map) {
        String username = SecurityUitls.getUserInfo().getUsername();
        boolean matches = passwordEncoder.matches(map.get("oldPassword"), map.get("newPassword"));
        return matches ? userMapper.updateUserOfPassword(username, map.get("newPassword"))>0 : false;
    }

    @Override
    public boolean sendVerificationCode(String email) {
        // html 邮件对象
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            String code = SecurityUitls.verificationCode();
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromMail);
            helper.setTo(email);
            helper.setSubject("验证码");
            helper.setText(code, true);
            javaMailSender.send(message);
            redisTemplate.opsForValue().set(email+":verificationCode",code,5, TimeUnit.MINUTES);
            log.info("发送邮件成功");
            return true;
        } catch (Exception e) {
            log.info("发送邮件失败");
            return false;
        }
    }



}
