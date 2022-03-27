package com.zhangheng.demo.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zhangheng.demo.util.Result;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JWTInterceptor implements HandlerInterceptor {

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        Result error = null;
        //认证token
        try {
            String token = request.getHeader("Authorization");//获得JWT
            if (token == null) throw new NullPointerException();
            Algorithm algorithm = Algorithm.HMAC256("david");//设置加密方式
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();//创建认证对象，创建失败会抛出AlgorithmMismatchException异常。
            DecodedJWT decodedJWT = jwtVerifier.verify(token.substring(7));//对去掉头信息的token进行验证
            int userId = decodedJWT.getClaim("userId").asInt();
            String userName = decodedJWT.getClaim("userName").asString();
            System.out.println(userId + "|" + userName);

        } catch (NullPointerException e) {
            error = Result.error(101, "请先登录后才等访问此资源！");//token为空代表未登录，跳转到登录页。
        } catch (AlgorithmMismatchException e) {
            error = Result.error(102, "token验证失败，请重新登录！");//token错误，跳转到登录页。
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (error != null) {
                //验证失败，向前端发送错误信息。
                response.setContentType("text/html; charset=utf-8");
                PrintWriter out = response.getWriter();
                out.println(error);
                out.close();
                return false;
            } else {
                //通过过滤器，进入controller。
                return true;
            }
        }
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

}