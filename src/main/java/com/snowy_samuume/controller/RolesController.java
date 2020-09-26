package com.snowy_samuume.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.snowy_samuume.entity.Roles;
import com.snowy_samuume.entity.User;
import com.snowy_samuume.service.RolesService;
import com.snowy_samuume.tool.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Security;

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

    @PostMapping
    @ApiOperation(value = "保存角色",notes = "保存角色")
    public R saveRoles(@RequestBody @Valid Roles roles){
        return R.ok(rolesService.save(roles));
    }

    @PutMapping
    @ApiOperation(value = "修改角色",notes = "修改角色")
    public R updateRoles(@RequestBody @Valid Roles roles){
        return R.ok(rolesService.updateById(roles));
    }

    @DeleteMapping("/rolesId")
    @ApiOperation(value = "修改角色",notes = "修改角色")
    public R deleteRoles(@PathVariable Integer rolesId){
        return R.ok(rolesService.removeById(rolesId));
    }

}
