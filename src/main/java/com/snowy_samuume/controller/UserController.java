package com.snowy_samuume.controller;

import com.snowy_samuume.entity.VO.UserVO;
import com.snowy_samuume.service.UserService;
import com.snowy_samuume.tool.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author snowy
 * @date 2020/9/19 21:35
 */
@RestController
@RequestMapping("/auth")
@Api(value = "用户模块",tags = {"用户模块"})
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ApiOperation(value = "用户注册",notes = "用户注册")
    public R saveUser(@RequestBody @Valid UserVO userVO){
        return R.ok(userService.saveUser(userVO));
    }

    @GetMapping("/getVerificatioCode")
    @ApiOperation(value = "给邮箱发送验证码",notes = "给邮箱发送验证码")
    public R getVerificatioCode(@NotBlank @Email String email){
        return  R.ok(userService.sendVerificationCode(email));
    }
}
