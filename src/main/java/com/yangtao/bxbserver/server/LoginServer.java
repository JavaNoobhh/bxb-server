package com.yangtao.bxbserver.server;

import com.yangtao.bxbserver.domain.User;
import com.yangtao.bxbserver.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public class LoginServer {

    @Autowired
    private LoginMapper loginMapper;

    public User Login(User user){
        return loginMapper.selectUser(user);
    }

}
