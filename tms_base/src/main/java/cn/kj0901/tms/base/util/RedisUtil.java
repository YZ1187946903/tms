package cn.kj0901.tms.base.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * KJ0901
 *
 * @Description :
 * @Author : Aedes
 * @Date: 2021/4/18 14:36
 */
@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void setToken(String token, String phone) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(token, phone,30L*60L , TimeUnit.SECONDS);
    }


    public String getToken(String token) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(token).toString();
    }

    public void setMsgCode(String code, String phone) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(code, phone, 60L , TimeUnit.SECONDS);
    }

    public String getMsg(String code) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(code);
    }


}
