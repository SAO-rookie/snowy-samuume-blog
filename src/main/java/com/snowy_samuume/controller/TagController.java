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
@RequestMapping("/tag")
@Api(value = "博客标签模块",tags = {"博客标签模块"})
public class TagController {
}
