package com.snowy_samuume.controller;

import com.snowy_samuume.entity.Type;
import com.snowy_samuume.mapper.TypeMapper;
import com.snowy_samuume.tool.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author snowy
 * @date 2020/9/19 21:32
 */
@RestController
@RequestMapping("/type")
@Api(value = "博客类型",tags = {"博客类型"})
public class TypeController {
    @Autowired
    private TypeMapper typeMapper ;
    @ApiOperation(value = "添加类型",notes = "添加类型")
    @PostMapping
    public R addType(){
        return R.ok();
    }
    @ApiOperation(value = "查询指定类型",notes = "查询指定类型")
    @GetMapping
    public Type selectTypeById(Integer TypeId){
        Type type=new Type();
        return  type;
    };
}
