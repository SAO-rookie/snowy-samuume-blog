package com.snowy_samuume.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snowy_samuume.entity.Roles;
import com.snowy_samuume.tool.other.Status;
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
public interface RolesService extends IService<Roles> {
    boolean saveRoleAndPermissions(Integer roleId,  List<Integer> permissions);

    boolean deleteOrDeactivateById(int id, Status status);
}
