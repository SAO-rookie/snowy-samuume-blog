package com.snowy_samuume.controller;

import com.snowy_samuume.entity.Type;
import com.snowy_samuume.mapper.TypeMapper;
import com.snowy_samuume.service.TypeService;
import com.snowy_samuume.tool.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author snowy
 * @date 2020/9/19 21:32
 */
@RestController
@RequestMapping("/type")
@Api(value = "博客类型",tags = {"博客类型"})
public class TypeController {
    @Autowired
    private TypeService typeService ;

    @ApiOperation(value = "添加类型",notes = "添加类型")
    @PostMapping
    public R addType(@RequestBody Type type){

        return R.ok(typeService.addType(type));

    }

    @ApiOperation(value = "删除类型",notes = "删除类型")
    @DeleteMapping("/{id}")
    public R deleteTypeById(@PathVariable Integer id){
        return R.ok(typeService.deleteTypeById(id));
    }

    @ApiOperation(value = "修改类型",notes ="修改类型")
    @PutMapping
    public R updateById(@RequestBody @Validated Type type){
        return R.ok(typeService.updateTypeById(type));
    }


    @ApiOperation(value = "查询指定类型",notes = "查询指定类型")
    @GetMapping("/{id}")
    public R selectTypeById(@PathVariable Integer id){
        return  R.ok(typeService.selectTypeById(id));
    };

    @ApiOperation(value = "查询全部类型",notes = "查询全部类型")
    @GetMapping
    public R selectAllType(){
        return R.ok(typeService.selectAllType());
    }



}
