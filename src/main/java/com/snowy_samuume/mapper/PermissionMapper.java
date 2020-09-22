package com.snowy_samuume.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.snowy_samuume.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author snowy
 * @date 2020/9/19 21:23
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    List<Permission> selectListByRolesId(@Param("rolesId") int rolesId);
}
