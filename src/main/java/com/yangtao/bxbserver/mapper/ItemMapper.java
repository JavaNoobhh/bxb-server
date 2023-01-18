package com.yangtao.bxbserver.mapper;

import com.yangtao.bxbserver.domain.Item;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemMapper {

    List<Item> selectItem(Item item);

    int insertItem(Item item);

    int deleteItem(int itemId);

    int updateItem(Item item);
}
