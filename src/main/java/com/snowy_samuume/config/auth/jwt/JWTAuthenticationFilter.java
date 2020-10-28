package com.snowy_samuume.config.auth.jwt;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snowy_samuume.entity.User;
import com.snowy_samuume.tool.JwtTokenUtils;
import com.snowy_samuume.tool.SpringBeanFactoryUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author snowy
 * @date 2020/9/21 21:50
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/auth/login");
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String captcha = request.getParameter("captcha");
        if (isCaptcha(captcha)){
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }
             throw new UsernameNotFoundException("账号密码错误");
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        User user = (User)authResult.getPrincipal();
        String jwtToken = JwtTokenUtils.generateToken(user);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setHeader(JwtTokenUtils.TOKEN_HANDER,JwtTokenUtils.TOKEN_PREFIX+jwtToken);
    }
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write("authentication failed, reason: " + failed.getMessage());
    }


    private boolean isCaptcha(String captcha){
        StringRedisTemplate bean = SpringBeanFactoryUtils.getBean(StringRedisTemplate.class);
        
        List<String> captchas = bean.opsForList().range("captcha",0, -1)
                .stream()
                .map(c->c.replace("\"",""))
                .filter(a->a.equalsIgnoreCase(captcha))
                .collect(Collectors.toList());
        if (ArrayUtil.isNotEmpty(captchas)){
            bean.opsForList().remove("captcha",1,"\""+captchas.get(0)+"\"");
            return true;
        }
            throw new UsernameNotFoundException("验证码错误");
    }
}
