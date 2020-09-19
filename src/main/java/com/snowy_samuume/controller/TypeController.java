package com.snowy_samuume.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author snowy
 * @date 2020/9/19 21:32
 */
@RestController
@RequestMapping("/type")
@Api(value = "博客类型",tags = {"博客类型"})
public class TypeController {
}
