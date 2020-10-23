package com.snowy_samuume.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.snowy_samuume.entity.User;
import com.snowy_samuume.service.UserService;
import com.snowy_samuume.tool.R;
import com.snowy_samuume.tool.SecurityUitls;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.IOException;

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

    @Autowired
    private RedisTemplate redisTemplate;

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
    @ApiOperation(value = "查询所有用户信息",notes = "查询所有用户信息")
    public R getUserPage(Page page){
        return R.ok(userService.page(page));
    }


    @GetMapping("/getVerificationCode")
    @ApiOperation(value = "给邮箱发送验证码",notes = "给邮箱发送验证码")
    public R getVerificationCode(@NotBlank @Email String email){
        return  R.ok(userService.sendVerificationCode(email));
    }

    @PostMapping("/register")
    @ApiOperation(value = "用户注册",notes = "用户注册")
    public R saveUser(@RequestBody @Valid User User){
        return R.ok(userService.saveUser(User));
    }

    @PutMapping
    @ApiOperation(value = "用户修改",notes = "用户修改")
    public R updateUser(@RequestBody @Valid User user){
        user.setPassword(null);
        user.setUpdateTime(DateUtil.date());
        user.setUpdateMan(SecurityUitls.getUserInfo().getId());
        return R.ok(userService.updateById(user));
    }

    @DeleteMapping("/{userId}")
    @ApiOperation(value = "用户删除",notes = "用户删除")
    public R updateUser(@PathVariable Integer userId){
        User user = new User();
        user.setId(userId);
        user.setStatus(2);
        return R.ok(userService.updateById(user));
    }

    @GetMapping("/getCaptcha")
    @ApiOperation(value = "获得图形验证码",notes = "获得图形验证码")
    public void getCaptcha(HttpServletResponse response){
        response.setContentType("image/jpeg");
        response.setCharacterEncoding("UTF-8");
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 100, 4, 4);
        try {
            captcha.write(response.getOutputStream());
            redisTemplate.opsForList().leftPush("captcha",captcha.getCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
