package com.snowy_samuume.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.snowy_samuume.entity.Type;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author snowy
 * @date 2020/9/19 21:20
 */
@Repository
public interface TypeMapper extends BaseMapper<Type> {
    Type selectTypeById(int id);

    List<Type> selectAllType();
}
