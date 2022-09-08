package cn.kj0901.tms.base.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * KJ0901
 *
 * @Description :
 * @Author : Aedes
 * @Date: 2021/4/18 14:36
 */
@Component
public class TokenUtil {

    @Autowired
    private RedisUtil redisUtil;

    public String getToken(String userJson) {
        String token = UUID.randomUUID().toString();
        redisUtil.setToken(token, userJson);
        return token;
    }

    public boolean checkToken(String token){
        return redisUtil.getToken(token)!=null;
    }

    public String getUser(String token){
        return redisUtil.getToken(token);
    }



}

