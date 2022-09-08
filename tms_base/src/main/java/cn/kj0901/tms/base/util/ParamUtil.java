package cn.kj0901.tms.base.util;

import cn.kj0901.tms.base.config.ResultJson;
import com.github.pagehelper.PageHelper;

import java.util.Map;

/**
 * KJ200901
 *
 * @Description :
 * @Author : Aedes
 * @Date: 2021/4/6 15:01
 */
public class ParamUtil {


    /**
    * 方法介绍
    *   验证参数
    * @author Aedes
    * @date 2021/4/6 15:59
    * @return cn.kj0901.tms.base.config.ResultJson
    * @throws
    */
    public static ResultJson checkParam(Map<String,Object> paraMap, String[] paramNames) {
        if(paraMap==null) {
            return ResultJson.err(201,"参数不能为空");
        }

        for (int i = 0; i < paramNames.length; i++) {
            String paramName = paramNames[i];
            Object param = paraMap.get(paramName);
            if(param==null||param.equals("")) {
                return ResultJson.err(201, paramName+"不能为空");
            }
        }
        return ResultJson.ok();
    }
    /**
    * 方法介绍
    *   验证并插入page信息
    * @author Aedes
    * @date 2021/4/6 16:01
    * @return void
    * @throws
    */
    public static void putPageInfo(Map<String,Object> parMap,Integer pageNum,Integer pageSize){
        if(parMap.get("pageNum")!=null && !"".equals(parMap.get("pageNum"))){
            pageNum = Integer.valueOf(parMap.get("pageNum").toString());
        }
        if(parMap.get("pageSize")!=null && !"".equals(parMap.get("pageSize"))){
            pageSize = Integer.valueOf(parMap.get("pageSize").toString());
        }
        PageHelper.startPage(pageNum, pageSize);
    }



}

