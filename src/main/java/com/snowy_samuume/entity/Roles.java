package com.snowy_samuume.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author snowy
 * @date 2020-09-19 20:52:57
 * @email 
 */
@Data
@ApiModel("角色id")
@TableName("sys_roles")
public class Roles  extends BaseEntity{
    //序列化ID
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "角色id")
    private Integer id;

    @NotBlank
    @ApiModelProperty(value = "角色名")
    private String roleName;

    @NotBlank
    @ApiModelProperty(value = "角色描述")
    private String roleDesc;

    @NotBlank
    @ApiModelProperty(value = "角色编码")
    private String roleCode;

}