package com.snowy_samuume.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snowy_samuume.entity.Permission;
import com.snowy_samuume.mapper.PermissionMapper;
import com.snowy_samuume.service.PermissionService;
import com.snowy_samuume.tool.SecurityUitls;
import com.snowy_samuume.tool.other.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public List<Permission> getListByRolesId(int rolesId) {
        return permissionMapper.selectListByRolesId(rolesId);
    }

    @Override
    public boolean savePermission(Permission permission) {
        permission.setCreateMan(SecurityUitls.getUserInfo().getId());
        permission.setPermissionPids("");
        return permissionMapper.insert(permission)>0;
    }

    @Override
    public boolean updatePermission(Permission permission) {
        permission.setUpdateMan(SecurityUitls.getUserInfo().getId());
        permission.setUpdateTime(new Date());
        return permissionMapper.updateById(permission)>0;
    }

    @Override
    public boolean deleteOrDeactivateById(int id, Status status) {
        Permission permission = new Permission();
        permission.setId(id);
        permission.setStatus(status.getValues());
        permission.setUpdateMan(SecurityUitls.getUserInfo().getId());
        permission.setUpdateTime(DateTime.now());
        return permissionMapper.updateById(permission)>0;
    }
}
