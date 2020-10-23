package com.snowy_samuume.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.*;
import java.util.Collection;

/**
 * @author snowy
 * @date 2020-09-19 20:52:57
 * @email 
 */
@Data
@ApiModel("用户表")
@TableName("sys_user")
public class User extends BaseEntity implements UserDetails {

    //序列化ID
    private static final long serialVersionUID = 1L;


    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "用户Id")
    private Integer id;

    @NotBlank
    @ApiModelProperty(value = "账号")
    private String username;

    @NotBlank
    @JsonIgnore
    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @NotNull
    @Max(value = 1,message = "最大一个")
    @ApiModelProperty(value = "性别 0男 1女 ")
    private Integer sex;

    @NotBlank
    @Email
    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "头像")
    private String avatar;


    @ApiModelProperty(value = "角色id")
    private Integer rolesId;

    @Null
    @TableField(exist = false)
    @ApiModelProperty(value = "用户的权限集合")
    private Collection<? extends GrantedAuthority> authorities;

    @NotBlank
    @JsonIgnore
    @ApiModelProperty(value = "验证码")
    @TableField(exist = false)
    private String verificationCode;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

}