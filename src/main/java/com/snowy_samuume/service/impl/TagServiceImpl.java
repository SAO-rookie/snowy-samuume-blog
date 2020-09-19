package com.snowy_samuume.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snowy_samuume.entity.Tag;
import com.snowy_samuume.mapper.TagMapper;
import com.snowy_samuume.service.TagService;
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
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}
