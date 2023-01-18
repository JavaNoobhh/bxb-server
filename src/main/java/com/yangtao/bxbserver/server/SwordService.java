package com.yangtao.bxbserver.server;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.yangtao.bxbserver.domain.*;
import com.yangtao.bxbserver.mapper.SwordMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Repository
public class SwordService {
    @Autowired
    private SwordMapper swordMapper;


    /**
     * 初始查询全部魔剑 以及条件查询魔剑
     * @param sword
     * @return
     */
    public PageInfo<Sword> selectSword(Sword sword,int pageNum,int pageSize) {

        PageHelper.startPage(pageNum,pageSize); //接收前端传来的分页数据分页 PageNum当前页数 PageNum分几页
        List<Sword> swords = swordMapper.selectSword(sword); //这里直接调用mapper的sql  这三个顺序就这样 不能乱
        PageInfo<Sword> pageInfo = new PageInfo<Sword>(swords);  //获取分页结果

        for (Sword sword1 : pageInfo.getList()) {  //pageInfo.getList()为分页好的数据  这里一整个都是获取里面的立绘信息 并存入数组中
            List<SwordPicture> swordPictures = swordMapper.selectPicture(sword1.getSwordId()); //获取当前魔剑的id 然后进行一次图片查找

            if(swordPictures.get(0) !=null ){
                String[] arr = new String[swordPictures.size()];   //初始化一个长度等于集合的长度的数组
                for(int i = 0;i<swordPictures.size();i++){
                    if(swordPictures.get(i)!=null){  //加一个判断 存一个空进去下面会报空指针
                        arr[i]  = swordPictures.get(i).getSwordPictureUrl(); //for 循环一个一个存进去图片
                    }
                }
                sword1.setSwordPicture2(arr);
            }
        }
        return pageInfo;
    }

    /**
     * 删除魔剑
     * @param id
     * @return
     */
    public int deleteSword(int id){
        swordMapper.deleteSwordPicture(id);  //调用一次删除立绘
        return swordMapper.deleteSwordById(id);
    }

    /**
     * 删除魔剑立绘
     * @param id
     * @return
     */
    public int deleteSwordPicture(int id){
        return swordMapper.deleteSwordPicture(id);
    }

    /**
     * 增加魔剑
     * @param sword
     * @return
     */
    public int insertSword(Sword sword){
        return swordMapper.insertSword(sword);
    }

    public List<Sword> selectSwordNameForInsert(String swordJname){
        return swordMapper.selectSwordNameForInsert(swordJname);
    }

    /**
     * 修改魔剑信息
     * @param sword
     * @return
     */
    public int updateSword(Sword sword){
        return swordMapper.updateSword(sword);
    }

    /**
     * 添加魔剑立绘
     * @param swordPicture
     * @return
     */
    public int insertPicture(SwordPicture swordPicture){
        return swordMapper.insertPicture(swordPicture);
    }

    public SwordCount selectCount(){
        return swordMapper.selectCount();
    }
    /**
     * 添加图片到七牛云 并返回图片地址
     * @param file
     * @return
     */
    public String uploadPicture(MultipartFile file){
        //文件名
        try {
            String path = setUploadManager(file.getInputStream(),file.getOriginalFilename());
            if (path == null){
                throw new Exception("输入的字符不能为空！");
            }
            return path;
        } catch (IOException e) {
            e.printStackTrace();
            return "文件上传失败";
        } catch (Exception e) {
            e.printStackTrace();
            return "文件上传失败";
        }
    }

    //    上传方法
    public String setUploadManager(InputStream inputStream, String swordName) {
        String accessKey = "4zTOff-GiRg1ADHfHf3i5bOfxIUAGbG3jNuP-HdF"; // AccessKey的值
        String secretKey = "ApNdEAL-Kni8tCE8T2c5-b5H7VuHvUhwGSZUhALg"; // SecretKey的值
        String bucket = "bxbpictureavatarfortaotaosama2"; // 存储空间名
//        String domain = "rm5x0yjmh.hn-bkt.clouddn.com";  bxbpictureavatarfortaotaosama这个过期了 只能读取不能上传了
        String domain = "rnp7d8z35.hn-bkt.clouddn.com";
        String urlname = ""; //获取到的图片url链接 (可以直接点开浏览的)
        //设置密钥、文件连接、文件名等等属性
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.huanan());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //设置连接地址
        Auth auth = Auth.create(accessKey, secretKey);
        String prefix = "";
        int Guid = 100;
        try {
            String s = auth.uploadToken(bucket);
            //生成文件名
//            String s1 = UUID.randomUUID().toString().replaceAll("-","");
            //实现文件上传
            Response response = uploadManager.put(inputStream, swordName, s, null, null);
            //解析上传成功结果
            DefaultPutRet defaultPutRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            //返回文件外链地址
            urlname = domain +"/"+defaultPutRet.key;
            return urlname;
        } catch (QiniuException e) {
            e.printStackTrace();
            return urlname;
        }
    }

    //查询用户对魔剑的评论
    public List<Comment> selectComment(int commentKind,int commentChannel){
        List<Comment> comments = swordMapper.selectComment(commentKind,commentChannel); //拿到所有的评论
        for (Comment comment : comments) {    //遍历每一条评论 用他们的id走一次接口获取对应的点赞总数 再存进去
            Integer commentId = comment.getCommentId();
            List<Like> likes = swordMapper.selectLikeUser(commentId);//用当前评论的id查询对其点赞的用户
            if (likes!=null){
                int[] arr = new int[likes.size()];
                for(int i = 0;i<likes.size();i++){
                    if(likes.get(i)!=null){  //加一个判断 存一个空进去下面会报空指针
                        arr[i]  = likes.get(i).getLikeUserId(); //for 循环将每一个点过赞的用户的id存进去
                    }
                }
                comment.setCommentLikeUser(arr);
            }
            comment.setCommentLikeCount(swordMapper.selectLikeCount(commentId));
        }
        return comments;
    }

    //添加用户对魔剑的评论
    public int insertComment(Comment comment){
        comment.setCommentTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return swordMapper.insertComment(comment);
    }

//    删除用户对魔剑的一条评论
    public int deleteDelete(int commentId){
        swordMapper.deleteCommentLike(commentId);
        return swordMapper.deleteComment(commentId);
    }

//    添加对魔剑评论的点赞
    public int insertLike(Like like){
        return swordMapper.insertLike(like);
    }


}
