package com.snowy_samuume.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snowy_samuume.entity.Type;
import com.snowy_samuume.mapper.TypeMapper;
import com.snowy_samuume.service.TypeService;
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
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {
    @Autowired
    private TypeMapper typeMapper;

    @Override
    public boolean addType(Type type) {
        int isExit= typeMapper.insert(type);
        return isExit > 0;
    }

    @Override
    public boolean deleteTypeById(Integer TypeId) {
        Type type=new Type();
        type.setId(TypeId);
        type.setStatus(1);
        int isExit= typeMapper.updateById(type);
        return isExit > 0;
    }

    @Override
    public boolean updateTypeById(Type type) {
        return typeMapper.updateById(type)>0;
    }

    @Override
    public Type selectTypeById(Integer TypeId) {
        return typeMapper.selectTypeById(TypeId);
    }

    @Override
    public List<Type> selectAllType() {
        return typeMapper.selectAllType();
    }


}
