package com.yangtao.bxbserver.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Crystal {
//    结晶id
    private Integer crystalId;

//    结晶日语名
    private String crystalJname;

//    结晶图片
    private String crystalImage;

//    结晶星数 1-6
    private Integer crystalStar;

//    结晶类型 攻击 攻速 转速 打蓝之类的
    private String crystalType;

//    结晶效果类型 浑身 背水 白值 这三个
    private String crystalEffectType;

//    结晶作用的武器类型  大剑 太刀之类的12种 在加上通用 专属
    private String crystalKind;

//    结晶作用属性 光 暗 无 风 火 水 通用 专属
    private String crystalElement;

//    结晶倍率 D~SS
    private String crystalPower;

//    结晶实际倍率
    private String crystalActualPower;

//    结晶上限值 绝超高之类的
    private String crystalUpLimit;

//    结晶效果
    private String crystalEffect;

//    结晶获取方式
    private String crystalAcquisition;
}
