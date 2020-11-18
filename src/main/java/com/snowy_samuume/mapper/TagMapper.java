package com.snowy_samuume.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.snowy_samuume.entity.Tag;
import com.snowy_samuume.entity.Type;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author snowy
 * @date 2020/9/19 21:21
 */
@Repository
public interface TagMapper extends BaseMapper<Tag> {

    List<Tag> selectAllTag();
}
