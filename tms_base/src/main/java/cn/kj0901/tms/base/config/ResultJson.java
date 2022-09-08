package cn.kj0901.tms.base.config;

import lombok.Data;

/**
 * KJ200901
 *
 * @Description :
 * @Author : Aedes
 * @Date: 2021/4/5 14:56
 */
@Data
public class ResultJson {

    private Integer code;
    private String msg;
    private Object data;

    public static ResultJson ok(){
        ResultJson rj = new ResultJson();
        rj.code = 200;
        rj.msg = "操作成功";

        return rj;
    }

    public static ResultJson ok(Object data){
        ResultJson rj = new ResultJson();
        rj.code = 200;
        rj.msg = "操作成功";
        rj.data = data;
        return rj;
    }

    public static ResultJson err(Integer code,String msg){
        ResultJson rj = new ResultJson();
        rj.code = code;
        rj.msg = msg;
        return rj;
    }



}

