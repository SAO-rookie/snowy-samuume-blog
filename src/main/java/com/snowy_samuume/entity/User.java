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
@ApiModel("用户表")
@TableName("sys_user")
public class User extends BaseEntity {

    @TableId
    @ApiModelProperty(value = "用户Id")
    private Integer id;

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "性别 0男 1女 ")
    private Integer sex;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "盐值")
    private String saltValue;

    @ApiModelProperty(value = "角色id")
    private Integer rolesId;

}