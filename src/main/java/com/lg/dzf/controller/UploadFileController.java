package com.lg.dzf.controller;

import com.lg.dzf.service.UploadService;
import com.lg.dzf.vo.UploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("api/upload")
public class UploadFileController {
    @Autowired
    private UploadService service;

    @RequestMapping("file")
    @ResponseBody
    public UploadResult uploadFile(MultipartFile file){
        return service.uploadFile(file);
    }
}
