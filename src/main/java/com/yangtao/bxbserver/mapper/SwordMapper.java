package com.yangtao.bxbserver.mapper;

import com.yangtao.bxbserver.domain.Sword;
import com.yangtao.bxbserver.domain.SwordCount;
import com.yangtao.bxbserver.domain.SwordPicture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface SwordMapper {

//    查询魔剑
    List<Sword> selectSword(Sword sword);

//    添加魔剑
    int insertSword(Sword sword);

//    修改魔剑
    int updateSword(Sword sword);

//    删除魔剑
    int deleteSwordById(int id);

//    删除魔剑关联立绘
    int deleteSwordPicture(int id);

//    添加图片
    int insertPicture(SwordPicture swordPicture);

//    查询立绘
    List<SwordPicture>selectPicture(int id);

    SwordCount selectCount();
}
