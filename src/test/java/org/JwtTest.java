package org;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    @Test
    public void testGen(){
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("username","张三");
        // 生成jwt代码
        String token = JWT.create()
                .withClaim("user",claims)
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*12)) //添加过期时间
                .sign(Algorithm.HMAC256("study"));
        System.out.println(token);
    }

    // 验证 有效期过、防修改-修改头部、载荷 验证失败
    @Test
    public void testParse(){
        // 定义字符串 模拟用户上传的token
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.ey" +
                "J1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IuW8oOS4iSJ9LCJleHAiOjE3NDIzMzUwMTZ9.pdEuY" +
                "Jkj_SyLIO-U02J8aqcEq02FmUFMtphZgZIahY8";
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("study")).build();
        DecodedJWT jwt = jwtVerifier.verify(token);
        System.out.println(jwt.getClaim("user"));
    }

}
