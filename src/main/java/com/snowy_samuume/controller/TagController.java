package com.snowy_samuume.controller;

import com.snowy_samuume.entity.Tag;
import com.snowy_samuume.entity.Type;
import com.snowy_samuume.service.TagService;
import com.snowy_samuume.service.TypeService;
import com.snowy_samuume.tool.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author snowy
 * @date 2020/9/19 21:35
 */
@RestController
@RequestMapping("/tag")
@Api(value = "博客标签模块",tags = {"博客标签模块"})
public class TagController {
    @Autowired
    private TagService tagService ;

    @ApiOperation(value = "添加标签",notes = "添加标签")
    @PostMapping
    public R addTag(@RequestBody Tag tag){
        return R.ok(tagService.addTag(tag));
    }

    @ApiOperation(value = "删除标签",notes = "删除标签")
    @DeleteMapping("/{id}")
    public R deleteTagById(@PathVariable Integer id){
        return R.ok(tagService.deleteTagById(id));
    }

    @ApiOperation(value = "修改标签",notes ="修改标签")
    @PutMapping
    public R updateById(@RequestBody @Validated Tag tag){
        return R.ok(tagService.updateTagById(tag));
    }

    @ApiOperation(value = "查询指定标签",notes = "查询指定标签")
    @GetMapping("/{id}")
    public R selectTagById(@PathVariable Integer id){
        return  R.ok(tagService.selectTagById(id));
    };

    @ApiOperation(value = "查询全部标签",notes = "查询全部标签")
    @GetMapping
    public R selectAllTag(){
        return R.ok(tagService.selectAllTag());
    }
}
