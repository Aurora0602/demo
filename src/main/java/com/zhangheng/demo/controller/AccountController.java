package com.zhangheng.demo.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.zhangheng.demo.entity.Account;
import com.zhangheng.demo.filter.JWTFilter;
import com.zhangheng.demo.service.AccountService;
import com.zhangheng.demo.util.Result;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * (Account)表控制层
 *
 * @author zhangheng
 * @since 2022-03-24 14:14:25
 */
@RestController
//@RequestMapping("account")
public class AccountController {
    /**
     * 服务对象
     */
    @Resource
    private AccountService accountService;


    /**
     * 通过主键查询单条数据
     *
     * @param account_id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Account selectOne(@RequestParam(defaultValue = "1") Integer account_id) {
        System.out.println(this.accountService.queryByAccount_id(account_id)+"1");
        return this.accountService.queryByAccount_id(account_id);
    }

    @PostMapping("check")
    public Result check(Account account) {
        Account retAccount = accountService.checkAccount(account);//签发token
        if (retAccount != null) {
            String token = JWT.create()
                    .withClaim("userId", retAccount.getAccount_id())
                    .withClaim("UserName", retAccount.getAccount_name())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1000*60*60))
                    .sign(Algorithm.HMAC256("david"));
            return Result.success(token);
        }else{
            return Result.fail("用户名或密码错误！");
        }
    }


}

