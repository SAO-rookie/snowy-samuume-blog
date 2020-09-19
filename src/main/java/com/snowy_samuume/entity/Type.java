package com.snowy_samuume.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author snowy
 * @date 2020-09-19 20:52:57
 * @email 
 */
@Data
@ApiModel("博客类型")
@TableName("t_type")
public class Type extends BaseEntity {

    @TableId
    @ApiModelProperty(value = "类型id")
    private Integer id;

    @ApiModelProperty(value = "类型名")
    private String typeName;

}