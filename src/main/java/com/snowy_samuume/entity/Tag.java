package com.snowy_samuume.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
@ApiModel("博客标签")
@TableName("t_tag")
public class Tag  extends BaseEntity {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "标签id")
    private Integer id;

    @ApiModelProperty(value = "标签名")
    private String tagName;

}