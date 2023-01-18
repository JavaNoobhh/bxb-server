package com.yangtao.bxbserver.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yangtao.bxbserver.domain.*;
import com.yangtao.bxbserver.mapper.SwordMapper;
import com.yangtao.bxbserver.server.SwordService;
import com.yangtao.bxbserver.tool.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/sword")
public class SwordController {

    @Autowired
    private SwordService swordService;

    @Autowired
    private SwordMapper swordMapper;

//    查询魔剑 前后端互传都在这里 全部查询跟条件查询都通过这个
    @GetMapping("/selectSword")
    public AjaxResult selectSword(Sword sword,int pageNum,int pageSize){
        return AjaxResult.success(swordService.selectSword(sword,pageNum,pageSize));
    }

//    删除单个魔剑 必须绑定id @PathVariable("id")
    @DeleteMapping("/deleteSword/{id}")
    public AjaxResult deleteSword( @PathVariable("id") int id){
        return AjaxResult.success(swordService.deleteSword(id));
    }

    @DeleteMapping("/deleteSwordPicture/{id}")
    public AjaxResult deleteSwordPicture( @PathVariable("id") int id){
        return AjaxResult.success(swordService.deleteSwordPicture(id));
    }

//    添加魔剑
    @PostMapping ("/insertSword")
    public AjaxResult insertSword(Sword sword){
        List<Sword> list = swordService.selectSwordNameForInsert(sword.getSwordJname());
        System.out.println(list);

        if(list!=null && list.size()>0){  //判断是否重复名字
            return AjaxResult.error(20001,"已经存在相同名字的魔剑,请确认后再次添加");
        }else {
            return AjaxResult.success(swordService.insertSword(sword));
        }
    }

    @PostMapping ("/updateSword")
    public AjaxResult updateSword(Sword sword){
        return AjaxResult.success(swordService.updateSword(sword));
    }

    @PostMapping ("/uploadPicture")
    public String uploadPicture(MultipartFile file){
        return swordService.uploadPicture(file);
    }

    @PostMapping("/insertPicture")
    public AjaxResult insertPicture(SwordPicture swordPicture){
        return AjaxResult.success(swordService.insertPicture(swordPicture));
    }

    //查询魔剑统计 暂时写这里
    @GetMapping("/selectCount")
    public AjaxResult selectCount(){
        return AjaxResult.success(swordService.selectCount());
    }

    //查询用户对魔剑的评论
    @GetMapping("/selectComment")
    public AjaxResult selectComment(int commentKind,int commentChannel){
        return AjaxResult.success(swordService.selectComment(commentKind,commentChannel));
    }

//    添加评论
    @PostMapping("/insertComment")
    public AjaxResult insertComment(Comment comment){
        return AjaxResult.success(swordService.insertComment(comment));
    }

//    删除评论
    @DeleteMapping("/deleteComment/{commentId}")
    public AjaxResult deleteComment( @PathVariable("commentId") int commentId){
        return AjaxResult.success(swordService.deleteDelete(commentId));
    }

//    添加点赞
    @PostMapping("/insertLike")
    public AjaxResult insertLike(Like like){
        return AjaxResult.success(swordService.insertLike(like));
    }



}



