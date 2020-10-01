package com.snowy_samuume.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snowy_samuume.entity.Roles;
import com.snowy_samuume.mapper.PermissionMapper;
import com.snowy_samuume.mapper.RolesMapper;
import com.snowy_samuume.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author snowy
 * @since 2020-09-19
 */
@Service
public class RolesServiceImpl extends ServiceImpl<RolesMapper, Roles> implements RolesService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public boolean saveRoleAndPermissions(Integer roleId, List<Integer> permissions) {
        return permissionMapper.bulkInsertRolesAndPermission(roleId, permissions)>0;
    }
}
