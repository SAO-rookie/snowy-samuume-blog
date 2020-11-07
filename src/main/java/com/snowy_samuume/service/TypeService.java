package com.snowy_samuume.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snowy_samuume.entity.Type;
import com.snowy_samuume.mapper.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author snowy
 * @since 2020-09-19
 */
public interface TypeService extends IService<Type> {

    boolean addType(Type type);

    boolean deleteTypeById(Integer TypeId);

    Type selectTypeById(Integer TypeId);

    List<Type> selectAllType();

    boolean updateTypeById(Type type);
}
