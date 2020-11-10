package com.snowy_samuume.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snowy_samuume.entity.Blog;
import com.snowy_samuume.tool.other.Status;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author snowy
 * @since 2020-09-19
 */
public interface BlogService extends IService<Blog> {

    boolean deleteOrRecycleById(String id, Status status);

}
