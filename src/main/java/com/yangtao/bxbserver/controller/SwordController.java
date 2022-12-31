package com.yangtao.bxbserver.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yangtao.bxbserver.domain.Page;
import com.yangtao.bxbserver.domain.Sword;
import com.yangtao.bxbserver.domain.SwordPicture;
import com.yangtao.bxbserver.mapper.SwordMapper;
import com.yangtao.bxbserver.server.SwordService;
import com.yangtao.bxbserver.tool.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public AjaxResult selectSword(Sword sword,Page page){
        return AjaxResult.success(swordService.selectSword(sword,page));
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


    @PostMapping ("/insertSword")
    public AjaxResult insertSword(Sword sword){
        return AjaxResult.success(swordService.insertSword(sword));
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

    @GetMapping("/selectCount")
    public AjaxResult selectCount(){
        return AjaxResult.success(swordService.selectCount());
    }



}



