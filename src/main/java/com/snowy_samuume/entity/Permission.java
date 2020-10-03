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
@ApiModel("权限标签")
@TableName("sys_permission")
public class Permission  extends BaseEntity{
    //序列化ID
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "许可id")
    private Integer id;

    @ApiModelProperty(value = "许可上级id")
    private Integer permissionPid;

    @ApiModelProperty(value = "许可上级所有id")
    private String permissionPids;

    @ApiModelProperty(value = "是否是叶子节点  0 是  1 不是")
    private Integer isLeaf;

    @ApiModelProperty(value = "许可名")
    private String permissionName;

    @ApiModelProperty(value = "许可码")
    private String permissionCode;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "菜单层级")
    private Integer level;

}