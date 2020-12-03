package com.snowy;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.snowy_samuume.BlogApplication;
import com.snowy_samuume.entity.Permission;
import com.snowy_samuume.service.PermissionService;
import com.snowy_samuume.service.RolesService;
import com.snowy_samuume.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author snowy
 * @date 2020/9/22 21:58
 */
@SpringBootTest(classes = BlogApplication.class)
@RunWith(SpringRunner.class)
public class test {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RolesService rolesService;

    @Test
    public void get(){
        List<Integer> collect = permissionService.list()
                .stream()
                .map(Permission::getId)
                .collect(Collectors.toList());

        rolesService.saveRoleAndPermissions(2,collect);
    }

    @Test
    public void oneTest(){
        List<Integer> integers = Arrays.asList(6, 7, 8, 13, 32, 33);
        rolesService.saveRoleAndPermissions(4,integers);
    }

}
