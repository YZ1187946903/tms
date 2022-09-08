package cn.kj0901.tms.base.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * KJ200901
 *
 * @Description :
 * @Author : Aedes
 * @Date: 2021/4/5 16:11
 */
@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultJson excHandler(Exception e){
        e.printStackTrace();
        return ResultJson.err(500,"未知异常,请联系管理员");
    }

}

