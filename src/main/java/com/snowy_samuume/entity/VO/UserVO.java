package com.snowy_samuume.entity.VO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.snowy_samuume.entity.Roles;
import com.snowy_samuume.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author snowy
 * @date 2020/9/26 15:06
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserVO implements Serializable {

    @NotBlank
    @ApiModelProperty(value = "id")
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

    @ApiModelProperty(value = "角色编码")
    private String rolesCode;

    @ApiModelProperty(value = "角色名")
    private String rolesName;

    @ApiModelProperty(value = "用户的权限集合")
    private List<String> authorities;

    @NotBlank
    @JsonIgnore
    @ApiModelProperty(value = "验证码")
    private String verificationCode;

    public static  UserVO getInstance(User user, Roles roles, List<String>  authorities){
        return new UserVO(user,roles,authorities);
    }
    public static  UserVO getInstance(User user){
        return new UserVO(user);
    }
    private UserVO(User user, Roles roles, List<String>  authorities) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.sex = user.getSex();
        this.email = user.getEmail();
        this.avatar = user.getAvatar();
        this.rolesName = roles.getRoleName();
        this.rolesCode = roles.getRoleCode();
        this.authorities = authorities;
    }

    private UserVO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.sex = user.getSex();
        this.email = user.getEmail();
        this.avatar = user.getAvatar();
    }
}
