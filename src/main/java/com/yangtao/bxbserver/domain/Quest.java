package com.yangtao.bxbserver.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quest {

//    副本id
    private Integer questId;

//    副本日语名
    private String questJname;

//    副本中文名
    private String questCname;

//    副本图片
    private String questImage;

//    副本类型  永续 限时活动 降临 超上之类的
    private String questKind;

//    副本描述
    private String questDescription;

//    副本掉落物 这个关联另一个 reward表获取
    private List<RewardForItem> questReward;
}
