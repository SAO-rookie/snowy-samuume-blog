package com.snowy_samuume.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel("用户表VO")
public class UserVo implements Serializable {
    //序列化ID
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户Id")
    private Integer id;

    @NotBlank
    @ApiModelProperty(value = "账号")
    private String username;


    @ApiModelProperty(value = "昵称")
    private String nickname;

    @Max(value = 1,message = "最大一个")
    @ApiModelProperty(value = "性别 0男 1女 ")
    private Integer sex;

    @Email
    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "头像")
    private String avatar;


    @ApiModelProperty(value = "角色id")
    private Integer rolesId;
}
