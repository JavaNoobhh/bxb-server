package com.yangtao.bxbserver.mapper;

import com.yangtao.bxbserver.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoginMapper {

    User selectUser(User user);

}
