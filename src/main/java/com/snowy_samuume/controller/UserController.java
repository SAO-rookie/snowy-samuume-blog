package com.snowy_samuume.controller;

import com.snowy_samuume.entity.User;
import com.snowy_samuume.service.UserService;
import com.snowy_samuume.tool.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author snowy
 * @date 2020/9/19 21:35
 */
@RestController
@RequestMapping("/auth")
@Api(value = "用户模块",tags = {"用户模块"})
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ApiOperation(value = "用户注册",notes = "用户注册")
    public R saveUser(@RequestBody User user){
        return R.ok(userService.saveUser(user));
    }
}
