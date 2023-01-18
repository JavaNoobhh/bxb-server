package com.yangtao.bxbserver.server;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.yangtao.bxbserver.domain.Crystal;
import com.yangtao.bxbserver.domain.Sword;
import com.yangtao.bxbserver.mapper.CrystalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@Repository
public class CrystalServer {

    @Autowired
    private CrystalMapper crystalMapper;

//    查询结晶
    public PageInfo<Crystal> selectCrystal(Crystal crystal,int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Crystal> crystals = crystalMapper.selectCrystal(crystal);
        PageInfo<Crystal> pageInfo = new PageInfo<Crystal>(crystals);
        return pageInfo;
    }

//    添加结晶
    public int insertCrystal(Crystal crystal){
        return crystalMapper.insertCrystal(crystal);
    }

//    删除结晶
    public int deleteCrystal(int id){
        return crystalMapper.deleteCrystal(id);
    }

//    修改结晶
    public int updateCrystal(Crystal crystal){
        return crystalMapper.updateCrystal(crystal);
    }

    public String uploadCrystalImage(MultipartFile file){
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


}
