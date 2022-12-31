package com.yangtao.bxbserver.controller;

import com.yangtao.bxbserver.domain.Page;
import com.yangtao.bxbserver.domain.Sword;
import com.yangtao.bxbserver.domain.Token;
import com.yangtao.bxbserver.domain.User;
import com.yangtao.bxbserver.mapper.LoginMapper;
import com.yangtao.bxbserver.server.LoginServer;
import com.yangtao.bxbserver.tool.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginServer loginServer;

    @GetMapping("/login")
    public AjaxResult userLogin(User user){
        User login = loginServer.Login(user);
        if (login==null){
            return AjaxResult.error("账号不存在");
        }else if (!loginServer.Login(user).getPassword().equals(user.getPassword())){
            System.out.println("xxxxxxxx");
            return AjaxResult.error("密码错误xxxx");

        }else {
//            Token myToken = new Token("admin-token");
            login.setToken("admin-token");
            return AjaxResult.success("登录成功",login);
        }
    }



}
