package com.snowy_samuume.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snowy_samuume.entity.Tag;
import com.snowy_samuume.entity.Type;
import com.snowy_samuume.mapper.TagMapper;
import com.snowy_samuume.service.TagService;
import com.snowy_samuume.tool.other.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author snowy
 * @since 2020-09-19
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public boolean addTag(Tag tag) {
        int isExit= tagMapper.insert(tag);
        return isExit >0;
    }

    @Override
    public boolean deleteOrDeactivateById(int TagId, Status status) {
        Tag tag = new Tag();
        tag.setId(TagId);
        tag.setStatus(status.getValues());
        int isExct=tagMapper.deleteById(TagId);
        return isExct>0;
    }

    @Override
    public Tag selectTagById(int TagId) {
        return tagMapper.selectById(TagId);
    }

    @Override
    public List<Tag> selectAllTag() {
        return tagMapper.selectAllTag();
    }

    @Override
    public boolean updateTagById(Tag tag) {
        return tagMapper.updateById(tag)>0;
    }
}
