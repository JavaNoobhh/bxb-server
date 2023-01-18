package com.yangtao.bxbserver.mapper;

import com.yangtao.bxbserver.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestMapper {

//    查询所有副本
    List<Quest> selectQuest(Quest quest);


    List<RewardForItem> selectQuestReward(int id);

//    查询副本对应的奖励类型
    List<String> selectRewardOfKind(int id);

//    通过奖励类型一个一个去关联找到奖励
    List<RewardForItem> selectRewardItem(int id);
    List<RewardForSword> selectRewardSword(int id);
    List<RewardForSoul> selectRewardSoul(int id);
    List<RewardForCrystal> selectRewardCrystal( int id);
}
