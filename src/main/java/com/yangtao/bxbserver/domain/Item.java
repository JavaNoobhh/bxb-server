package com.yangtao.bxbserver.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

//    物品id
    private Integer itemId;

//    物品日语名
    private String itemJname;

//    物品中文名
    private String itemCname;

//    物品图片
    private String itemImage;

//    物品描述补充
    private String itemDescription;
}
