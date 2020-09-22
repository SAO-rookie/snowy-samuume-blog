package com.snowy_samuume.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "用户Id")
    private Integer id;

    @NotBlank
    @ApiModelProperty(value = "账号")
    private String username;

    @NotBlank
    @ApiModelProperty(value = "密码")
    private String password;


    @ApiModelProperty(value = "昵称")
    private String nickname;

    @NotBlank
    @Size(max = 1)
    @ApiModelProperty(value = "性别 0男 1女 ")
    private Integer sex;

    @NotBlank
    @Email
    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "盐值")
    private String saltValue;

    @ApiModelProperty(value = "角色id")
    private Integer rolesId;

    @ApiModelProperty(value = "用户的权限集合")
    @TableField(exist = false)
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}