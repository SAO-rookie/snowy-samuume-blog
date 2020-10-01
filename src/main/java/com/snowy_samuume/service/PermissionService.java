package com.snowy_samuume.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snowy_samuume.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author snowy
 * @since 2020-09-19
 */
public interface PermissionService extends IService<Permission> {
    List<Permission> getListByRolesId(int rolesId);

    boolean savePermission(Permission permission);

    boolean updatePermission(Permission permission);

}
