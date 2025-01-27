package com.zry.interceptor;

import com.zry.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求路径
        String requestURI = request.getRequestURI();

        //判断是否是登录请求
        if(requestURI.contains("/login"))
        {
            log.info("这是一个登录请求，放行");
            return true;
        }

        //获取请求头中的token
        String token = request.getHeader("token");

        //判断token  token不存在，返回401错误
        if(token == null || token.isEmpty())
        {
            log.info("令牌为空，响应401");
            response.setStatus(401);
            return false;
        }

        //token存在，校验token
        try
        {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            log.info("令牌非法，响应401");
            response.setStatus(401);
            return false;
        }
        //通过则放行
        log.info("令牌合法，放行");
        return true;
    }
}
