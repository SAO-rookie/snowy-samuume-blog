package com.snowy_samuume.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snowy_samuume.entity.Blog;
import com.snowy_samuume.mapper.BlogMapper;
import com.snowy_samuume.service.BlogService;
import com.snowy_samuume.tool.SecurityUitls;
import com.snowy_samuume.tool.other.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author snowy
 * @since 2020-09-19
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Autowired
    private BlogMapper blogMapper;


    @Override
    public boolean deleteOrRecycleById(String id, Status status) {
        Blog blog = new Blog();
        blog.setId(id);
        blog.setStatus(status.getValues());
        blog.setUpdateMan(SecurityUitls.getUserInfo().getId());
        blog.setUpdateTime(DateTime.now());
        return blogMapper.updateById(blog)>0;
    }
}
