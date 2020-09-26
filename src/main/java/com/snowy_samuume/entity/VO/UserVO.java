package com.snowy_samuume.entity.VO;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author snowy
 * @date 2020/9/26 15:06
 */
@Data
public class UserVO implements Serializable {

    @NotBlank
    @ApiModelProperty(value = "账号")
    private String username;

    @NotBlank
    @JSONField(serialize=false)
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

    @ApiModelProperty(value = "用户的权限集合")
    private Collection<? extends GrantedAuthority> authorities;

    @NotBlank
    @ApiModelProperty(value = "验证码")
    private String verificationCode;
}
