package com.lg.dzf.service;

import com.lg.dzf.vo.UploadResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
public class UploadService {

    /**
     * 上传图片
     * @param file
     * @return
     */
    public UploadResult uploadFile(MultipartFile file) {
        //生成一个唯一字符串
        String name= UUID.randomUUID().toString();
        //获取到原来的文件名
        String ysname=file.getOriginalFilename();
        //获取后缀名
        String extName=ysname.substring(ysname.lastIndexOf("."));
        //组成新的名字
        String reName=name+extName;
        File image = new File("C:/images/"+reName);
        try {
            file.transferTo(image);
        } catch (IOException e) {
            log.error("图片上传失败：{}", file);
            e.printStackTrace();
        }
        //返回视图
        return new UploadResult("0", "上传成功", reName);
    }
}