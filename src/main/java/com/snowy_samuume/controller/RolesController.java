package com.snowy_samuume.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.snowy_samuume.entity.Roles;
import com.snowy_samuume.entity.User;
import com.snowy_samuume.service.RolesService;
import com.snowy_samuume.tool.R;
import com.snowy_samuume.tool.other.Status;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.security.Security;
import java.util.List;
import java.util.Map;

/**
 * @author snowy
 * @date 2020/9/19 21:38
 */
@RestController
@RequestMapping("/roles")
@Api(value = "角色模块",tags = {"角色模块"})
public class RolesController {
    @Autowired
    private RolesService rolesService;

    @GetMapping("/page")
    @ApiOperation(value = "获得角色分页",notes = "获得角色分页")
    public R getRolesPage(Page page){
        return R.ok(rolesService.page(page));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询角色",notes = "根据id查询角色")
    public R getRolesById(@PathVariable Integer id){
        return R.ok(rolesService.getById(id));
    }


    @PostMapping
    @ApiOperation(value = "保存角色",notes = "保存角色")
    public R saveRoles(@RequestBody @Valid Roles roles){
        return R.ok(rolesService.save(roles));
    }

    @PostMapping("/RoleAndPermissions/{roleId}")
    @ApiOperation(value = "根据角色Id保存权限",notes = "根据角色Id保存权限")
    public R saveRoleAndPermissions(@PathVariable Integer roleId, @RequestBody List<Integer> permissions){
        return R.ok(rolesService.saveRoleAndPermissions(roleId,permissions));
    }

    @PutMapping
    @ApiOperation(value = "修改角色",notes = "修改角色")
    public R updateRoles(@RequestBody @Valid Roles roles){
        return R.ok(rolesService.updateById(roles));
    }

    @DeleteMapping("/rolesId")
    @ApiOperation(value = "根据id删除角色",notes = "根据id删除角色")
    public R deleteRoles(@PathVariable Integer rolesId){
        return R.ok(rolesService.deleteOrDeactivateById(rolesId, Status.DELETE));
    }

    @DeleteMapping("/deactivate/rolesId")
    @ApiOperation(value = "根据id停用角色",notes = "根据id停用角色")
    public R deactivateRoles(@PathVariable Integer rolesId){
        return R.ok(rolesService.deleteOrDeactivateById(rolesId, Status.RECYCLING_OR_DEACTIVATE));
    }

}
