package com.zry.config;

import com.zry.interceptor.DemoInterceptor;
import com.zry.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    //自定义的拦截器对象
    /*@Autowired
    private DemoInterceptor demoInterceptor;*/

    @Autowired
    private TokenInterceptor tokenInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器对象
        //registry.addInterceptor(demoInterceptor).addPathPatterns("/**").excludePathPatterns("/login");//设置拦截器拦截的请求路径（ /** 表示拦截所有请求）
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**");
    }
}
