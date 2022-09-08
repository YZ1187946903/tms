package cn.kj0901.tms.common.controller;

import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * KJ200901
 *
 * @Description : 处理文件上传
 * @Author : Aedes
 * @Date: 2021/4/8 14:12
 */
@Slf4j
@RestController
@RequestMapping("file")
public class FileController {
    @Value("${files.upPath}")
    private String path;

    @Value("${files.showUrl}")
    private String url;

    @PostMapping("upload")
    @ResponseBody
    public ResultJson upload(@RequestParam("file") MultipartFile file){

        //1.获取文件
        if(file.isEmpty()){
            return ResultJson.err(201,"文件必须上传");
        }

        //2.处理文件存储的路径
        String suffixName = FileUtil.getFileSuffixName(file);
        String newFileName = FileUtil.createNewFileName(suffixName);
        String filePath = path+newFileName;

        try {
            //3.完成存储
            File saveFile = new File(filePath);
            file.transferTo(saveFile);

            //4.处理文件的url并返回
            log.info("文件上传成功");
            return ResultJson.ok(url+newFileName);
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return ResultJson.err(205,"上传失败");

    }

}

