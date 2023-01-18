package com.yangtao.bxbserver.mapper;

import com.yangtao.bxbserver.domain.Crystal;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CrystalMapper {

//    查询结晶
    List<Crystal> selectCrystal(Crystal crystal);

//    添加结晶
    int insertCrystal(Crystal crystal);

//    删除结晶
    int deleteCrystal(int id);

//    修改结晶
    int updateCrystal(Crystal crystal);
}
