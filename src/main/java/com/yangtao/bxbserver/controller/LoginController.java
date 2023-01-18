package com.yangtao.bxbserver.controller;

import com.yangtao.bxbserver.domain.*;
import com.yangtao.bxbserver.server.LoginServer;
import com.yangtao.bxbserver.tool.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private LoginServer loginServer;

    @GetMapping("/login")
    public AjaxResult userLogin(User user){
        User login = loginServer.Login(user);
//        System.out.println("输出login"+login);
        if (login==null){
            return AjaxResult.error("账号不存在");
        }else if (!loginServer.Login(user).getPassword().equals(user.getPassword())){
            return AjaxResult.error("密码或密码错误");

        }else {
            String myToken = "admin-token-"+new Date();
            //生成token与用户对象的 键值对 然后将这对键值对存入缓存 然后设置缓存失效时间
            Jedis jedis = new Jedis("127.0.0.1", 6379);
            jedis.hset(myToken,"userId",login.getUserId());
            jedis.hset(myToken,"name",login.getUsername());
            jedis.hset(myToken,"avatar",login.getUserAvatar());
            jedis.hset(myToken,"roles",login.getRoles());
            jedis.hset(myToken,"introduction",login.getIntroduction());
            jedis.expire(myToken,60*60);
            Map<String, String> stringStringMap = jedis.hgetAll(myToken);

            return AjaxResult.success("登录成功",myToken);
        }
    }

    @GetMapping("/getInfo")
    public AjaxResult getInfo(String myToken){
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(jedis.hget(myToken,"userId"));
        userInfo.setName(jedis.hget(myToken,"name"));
        userInfo.setAvatar(jedis.hget(myToken,"avatar"));
        String[] arr = {jedis.hget(myToken,"roles")};
        userInfo.setRoles(arr);
        userInfo.setIntroduction(jedis.hget(myToken,"introduction"));
        //查询对应的token 返回token对应的对象信息
        return AjaxResult.success(userInfo);
    }



}
