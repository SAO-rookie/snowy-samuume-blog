package com.snowy_samuume.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.snowy_samuume.entity.Blog;
import com.snowy_samuume.service.BlogService;
import com.snowy_samuume.tool.IdWorker;
import com.snowy_samuume.tool.R;
import com.snowy_samuume.tool.SecurityUitls;
import com.snowy_samuume.tool.other.Status;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author snowy
 * @date 2020/9/19 21:30
 */
@RestController
@RequestMapping("/blog")
@Api(value = "博客模块",tags = {"博客模块"})
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private IdWorker idWorker;

    @GetMapping("/page")
    @ApiOperation(value = "博客分页",notes = "博客分页")
    public R getPageList(Page page){
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.eq("status",0);
        return R.ok(blogService.page(page,wrapper));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询",notes = "根据id查询")
    public R getById(@PathVariable String id){
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        wrapper.eq("status",Status.NORMAL.getValues());
        return R.ok(blogService.getOne(wrapper));
    }

    @PostMapping
    @ApiOperation(value = "博客新增",notes = "博客新增")
    public R save(@Validated @RequestBody Blog blog){
        blog.setId(String.valueOf(idWorker.nextId()));
        blog.setCreateMan(SecurityUitls.getUserInfo().getId());
        return R.ok(blogService.save(blog));
    }

    @PutMapping
    @ApiOperation(value = "博客修改",notes = "博客修改")
    public R updateById(@RequestBody Blog blog){
        return R.ok(blogService.updateById(blog));
    }



    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据ID删除",notes = "根据ID删除")
    public R deleteById(@PathVariable String id){
        return R.ok(blogService.deleteOrRecycleById(id, Status.DELETE));
    }

    @PutMapping("/recycle/{id}")
    @ApiOperation(value = "根据ID回收",notes = "根据ID回收")
    public R recycleById(@PathVariable String id){
        return R.ok(blogService.deleteOrRecycleById(id, Status.RECYCLING_OR_DEACTIVATE));
    }



}
