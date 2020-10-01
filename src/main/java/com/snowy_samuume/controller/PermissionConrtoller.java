package com.snowy_samuume.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.snowy_samuume.entity.Permission;
import com.snowy_samuume.service.PermissionService;
import com.snowy_samuume.tool.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Security;

/**
 * @author snowy
 * @date 2020/9/19 21:37
 */
@RestController
@RequestMapping("/permission")
@Api(value = "权限模块",tags = {"权限模块"})
public class PermissionConrtoller {
    @Autowired
    private PermissionService permissionService;

    @GetMapping("/page")
    @ApiOperation(value = "权限列表检查",notes = "权限列表检查")
    public R getPage(Page page){
        return  R.ok(permissionService.page(page));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据权限id查询" , notes = "根据权限id查询")
    public R getOneById(@PathVariable Integer id){
        return R.ok(permissionService.getById(id));
    }
    @PostMapping
    @ApiOperation(value = "保存权限信息" , notes = "保存权限信息")
    public R save(@RequestBody Permission permission){
        return R.ok(permissionService.savePermission(permission));
    }

    @PutMapping
    @ApiOperation(value = "保存权限信息" , notes = "保存权限信息")
    public R update(@RequestBody Permission permission){
        return R.ok(permissionService.updatePermission(permission));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除权限信息" , notes = "删除权限信息")
    public R deleteById(@PathVariable Integer id){
        return R.ok(permissionService.removeById(id));
    }



}
