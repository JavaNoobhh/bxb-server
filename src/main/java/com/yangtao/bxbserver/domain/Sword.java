package com.yangtao.bxbserver.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sword {

    //魔剑id
    private Integer swordId;

    //魔剑图片
    private String swordPicture;

    //魔剑日语名
    private String swordJname;

    //魔剑中文简称
    private String swordCname;

    //魔剑稀有度
    private String swordRare;

    //魔剑是否为限定
    private Integer swordLimit;

    //魔剑属性
    private String swordElement;

    //魔剑武器类型
    private String swordKind;

    //魔剑最大hp
    private Integer swordHp;

    //魔剑最大攻击
    private Integer swordAtk;

    //魔剑最大防御
    private Integer swordDef;

    //魔剑最大破防值
    private Integer swordBreak;

    //魔剑最大槽(非好感)
    private Integer swordHole;

    //魔剑满hit
    private Integer swordHit;

    //魔剑bd费用
    private Integer swordBdCost;

    //魔剑技能
    private String swordSkill;

//    上传token 用于上传七牛云
    private String token;

//    魔剑立绘
//    private List<SwordPicture> sPicture;

    private String[] swordPicture2;
//
}
