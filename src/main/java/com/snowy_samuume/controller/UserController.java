package com.snowy_samuume.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.snowy_samuume.entity.VO.UserVO;
import com.snowy_samuume.service.UserService;
import com.snowy_samuume.tool.R;
import com.snowy_samuume.tool.SecurityUitls;
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

    @GetMapping("/info")
    @ApiOperation(value = "获取当前用户信息",notes = "获取当前用户信息")
    public R getCurrentUserInfo(){
        return R.ok(SecurityUitls.getUserInfo());
    }

    @GetMapping("/user/{userId}")
    @ApiOperation(value = "根据Id查询用户信息",notes = "根据Id查询用户信息")
    public R getUserInfoById(@PathVariable Integer userId){
        return R.ok(userService.getUserInfoById(userId));
    }

    @GetMapping("/page")
    @ApiOperation(value = "根据Id查询用户信息",notes = "根据Id查询用户信息")
    public R getUserPage(Page page){
        return R.ok(userService.page(page));
    }

    @PostMapping("/register")
    @ApiOperation(value = "用户注册",notes = "用户注册")
    public R saveUser(@RequestBody @Valid UserVO userVO){
        return R.ok(userService.saveUser(userVO));
    }

    @GetMapping("/getVerificationCode")
    @ApiOperation(value = "给邮箱发送验证码",notes = "给邮箱发送验证码")
    public R getVerificationCode(@NotBlank @Email String email){
        return  R.ok(userService.sendVerificationCode(email));
    }

}
