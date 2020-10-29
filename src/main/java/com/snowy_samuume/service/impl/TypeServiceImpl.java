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
    TypeMapper typeMapper;

    @Override
    public void addType(Type type) {
        typeMapper.insert(type);
    }

    @Override
    public void deleteTypeById(Integer TypeId) {
        typeMapper.deleteById(TypeId);
    }

    @Override
    public Type selectTypeById(Integer TypeId) {
        return typeMapper.selectById(TypeId);
    }

    @Override
    public List<Type> selectAllType() {
        return null;
    }

    @Override
    public void updateTypeById(Integer TypeId) {

    }
    @Override
    public void deleteTypeByName(String typeName) {

    }
}
