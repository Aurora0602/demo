package com.zhangheng.demo.config;

import com.zhangheng.demo.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class JWTConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(new JWTInterceptor());
        registration.addPathPatterns("/**");                     //所有路径都被拦截
        registration.excludePathPatterns(                        //添加不拦截路径
                "/check",                //登录验证
                "/login.html",
                "/**/*.html",            //html静态资源
                "/**/*.js",              //js静态资源
                "/**/*.css",             //css静态资源
                "../static/img",
                "/**/*.svg",
                "/**/*.png",
                "/**/*.woff",
                "/**/*.ico",
                "/**/*.ttf"
        );
    }
}
