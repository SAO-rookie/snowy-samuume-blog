package com.snowy_samuume.tool;

import com.snowy_samuume.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author snowy
 * @date 2020/9/20 22:44
 */
public class JwtTokenUtils {

    public static final  String TOKEN_HANDER = "Authorization";
    public static final  String TOKEN_PREFIX ="Bearer ";
    // 秘钥
    private  static final String SECRET = "jwtSECRET";
    // 过期时间是3600秒，既是1个小时
    private static final long EXPIRATION = 1000 * 60 * 60L;


    /**
     * 生成token令牌
     *
     * @param user 用户
     * @return 令token牌
     */
    public static String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>(2);
        claims.put("sub", user.getUsername());
        claims.put("created", new Date());

        return generateToken(claims);
    }

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public static String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public static Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 刷新令牌
     *
     * @param token 原令牌
     * @return 新令牌
     */
    public static String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put("created", new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * 验证令牌
     *
     * @param token       令牌
     * @param user 用户
     * @return 是否有效
     */
    public static Boolean validateToken(String token, User user) {

        String username = getUsernameFromToken(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }


    /**
     * 从claims生成令牌,如果看不懂就看谁调用它
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    /**
     * 从令牌中获取数据声明,如果看不懂就看谁调用它
     *
     * @param token 令牌
     * @return 数据声明
     */
    private static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public static void main(String[] args) {
        User user = new User();
        user.setUsername("dasdas");
        String s = generateToken(user);
        String usernameFromToken = getUsernameFromToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYwMDc4MjcyMywiaWF0IjoxNjAwNzgyNzIwLCJjcmVhdGVkIjoxNjAwNzgyNzIwMDc1fQ.vCMOtWstUs8helr9dcYAKf8zi6-QEFBCx7In_UAWMsyvF_7qjUQwmWeu6kG6jI62GzAQO4uRXyG4W3qHvv8feA");
         System.out.println(usernameFromToken);

    }
}