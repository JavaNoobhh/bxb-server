package com.yangtao.bxbserver.server;

import com.yangtao.bxbserver.domain.*;
import com.yangtao.bxbserver.mapper.QuestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Repository
public class QuestServer {

    @Autowired
    private QuestMapper questMapper;

    public List<Quest> selectQuest(Quest quest){
        List<Quest> quests = questMapper.selectQuest(quest);   //先查询所有副本
        for (Quest quest1 : quests) {        //循环查询每一个副本的id 用id找到对应奖励表
            Integer id = quest1.getQuestId();  //拿到当前副本的id
            List<String> rewardList = questMapper.selectRewardOfKind(id);   //拿到对应的奖励表
            System.out.println(rewardList);

//            for (String rewards : rewardList) {
//                List<RewardForSword> rewardForSwords = questMapper.selectRewardSword(id);
//                List<RewardForSoul> rewardForSouls = questMapper.selectRewardSoul(id);
//                List<RewardForCrystal> rewardForCrystals = questMapper.selectRewardCrystal(id);
//                List<RewardForItem> rewardForItems = questMapper.selectRewardItem(id);
//            }

            List<RewardForItem> questRewards = questMapper.selectQuestReward(id);

            quest1.setQuestReward(questRewards);
        }
        return quests;
    }
}
