package com.yangtao.bxbserver.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    private String userId;
    private String name;
    private String avatar;
    private String[] roles;
    private String introduction;
}
