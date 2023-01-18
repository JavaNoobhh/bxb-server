package com.yangtao.bxbserver.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reward {

    private Integer rewardId;
    private String rewardQuest;
    private String rewardKind;
    private String rewardItemId;

}
