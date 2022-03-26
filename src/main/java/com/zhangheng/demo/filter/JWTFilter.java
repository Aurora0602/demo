package com.zhangheng.demo.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zhangheng.demo.util.Result;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("*")
public class JWTFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //可以扩展多个不需要验证页面地址。
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Result error = null;
        //认证token
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String url = request.getRequestURL().toString();
        String path = url.substring(url.lastIndexOf("/") + 1);
        try {
            //判断url是否需要验证过程
            if (!path.equals("check")) {
                String token = request.getHeader("Authorization");//获得JWT
                if (token == null) throw new NullPointerException();
                Algorithm algorithm = Algorithm.HMAC256("david");//设置加密方式
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();//创建认证对象，创建失败会抛出AlgorithmMismatchException异常。
                DecodedJWT decodedJWT = jwtVerifier.verify(token.substring(7));//对去掉头信息的token进行验证
                int userId = decodedJWT.getClaim("userId").asInt();
                String userName = decodedJWT.getClaim("userName").asString();
                System.out.println(userId + "|" + userName);
            }
        } catch (NullPointerException e) {
            error = Result.error(101, "请先登录后才等访问此资源！");//token为空代表未登录，跳转到登录页。
        } catch (AlgorithmMismatchException e) {
            error = Result.error(102, "token验证失败，请重新登录！");//token错误，跳转到登录页。
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (error != null) {
            //验证失败，向前端发送错误信息。
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println(error);
            out.close();
        } else {
            //通过过滤器，进入controller。
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
