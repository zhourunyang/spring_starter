package com.zry;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    /**
     * 生成Jwt令牌   Jwts.builder()
     */
    @Test
    public void TestJwt(){
        Map<String,Object> dataMap = new HashMap();
        dataMap.put("age",21);
        dataMap.put("name","admin");
        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256,"Sunday")  //指定加密算法
                .addClaims(dataMap)  //添加自定义信息
                .setExpiration(new Date(System.currentTimeMillis() + 3600*1000))  //设置过期时间
                .compact();

        System.out.println(jwt);
    }

    /**
     * 解析Jwt令牌
     */
    @Test
    public void testParseJwt()
    {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiYWRtaW4iLCJhZ2UiOjIxLCJleHAiOjE3Mzc5NzM0Mzh9.EZu9e0oq4HIBsPr-9V1AxIyYVR0wfFea33fIC-q-z54";
        Claims claims = Jwts.parser().setSigningKey("Sunday")
                .parseClaimsJws(token)
                .getBody();
        System.out.println(claims);
    }
}
