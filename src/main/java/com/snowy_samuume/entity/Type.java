package com.snowy_samuume.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author snowy
 * @date 2020-09-19 20:52:57
 * @email 
 */
@Data
@ApiModel("博客类型")
@TableName("t_type")
public class Type extends BaseEntity {
    //序列化ID
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不能为空")
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "类型id")
    private Integer id;

    @ApiModelProperty(value = "类型名")
    private String typeName;

}