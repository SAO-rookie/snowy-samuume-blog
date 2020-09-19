package com.snowy_samuume.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author snowy
 * @date 2020/9/19 21:35
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户模块",tags = {"用户模块"})
public class UserController {
}
