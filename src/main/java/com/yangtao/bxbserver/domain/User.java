package com.yangtao.bxbserver.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String userId;
    private String username;
    private String password;
    private String userAvatar;
    private String roles;
    private String introduction;
}
