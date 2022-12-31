package com.yangtao.bxbserver.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SwordPicture {

//    存储图片编号id
    private Integer swordPictureId;

//    对应的魔剑id
    private Integer swordId;

//    图片连接地址
    private String swordPictureUrl;
}
