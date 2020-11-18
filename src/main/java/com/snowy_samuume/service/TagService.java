package com.snowy_samuume.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snowy_samuume.entity.Tag;
import com.snowy_samuume.entity.Type;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author snowy
 * @since 2020-09-19
 */
public interface TagService extends IService<Tag> {
    boolean addTag(Tag tag);

    boolean deleteTagById(Integer TagId);

    Tag selectTagById(Integer TagId);

    List<Tag> selectAllTag();

    boolean updateTagById(Tag tag);
}
