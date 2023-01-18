package com.yangtao.bxbserver.mapper;

import com.yangtao.bxbserver.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface SwordMapper {

//    查询魔剑
    List<Sword> selectSword(Sword sword);

//    添加魔剑前的查找重复验证
    List<Sword> selectSwordNameForInsert(String swordJname);

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

//    查询用户对魔剑的评论
    List<Comment> selectComment(int commentKind,int commentChannel);

//    添加对魔剑的评论
    int insertComment(Comment comment);

//    删除对魔剑的评论
    int deleteComment(int commentId);

//    给魔剑评论点赞
    int insertLike(Like like);

//    查询该评论点赞总数
    int selectLikeCount(int likeCommentId);

//    用当前评论的id查询对其点赞的用户
    List<Like>selectLikeUser(int commentId);

    int deleteCommentLike(int commentId);
}
