package cn.kj0901.tms.base.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * KJ200901
 *
 * @Description : 处理文件的工具
 * @Author : Aedes
 * @Date: 2021/4/8 14:21
 */
public class FileUtil {
    /**
     * 获取文件后缀名
     * @param file
     * @return
     */
    public static String getFileSuffixName(MultipartFile file){
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        return suffixName;
    }


    /**
     * 获取新文件名
     * @param suffixName
     * @return
     */
    public static String createNewFileName(String suffixName){
        String timeStr = DateUtil.format(new Date(),"yyyyMMddHHmmss");
        String ranStr = RandomUtil.randomString(4);
        return timeStr+ranStr+suffixName;

    }


}

