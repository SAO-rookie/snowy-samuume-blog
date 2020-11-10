package com.snowy_samuume.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snowy_samuume.entity.Roles;
import com.snowy_samuume.mapper.PermissionMapper;
import com.snowy_samuume.mapper.RolesMapper;
import com.snowy_samuume.service.RolesService;
import com.snowy_samuume.tool.SecurityUitls;
import com.snowy_samuume.tool.other.Status;
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

    @Autowired
    private RolesMapper rolesMapper;

    @Override
    public boolean saveRoleAndPermissions(Integer roleId, List<Integer> permissions) {
        return permissionMapper.bulkInsertRolesAndPermission(roleId, permissions)>0;
    }

    @Override
    public boolean deleteOrDeactivateById(int id, Status status) {
        Roles roles = new Roles();
        roles.setId(id);
        roles.setStatus(status.getValues());
        roles.setUpdateMan(SecurityUitls.getUserInfo().getId());
        roles.setUpdateTime(DateTime.now());
        return rolesMapper.updateById(roles)>0;
    }
}
