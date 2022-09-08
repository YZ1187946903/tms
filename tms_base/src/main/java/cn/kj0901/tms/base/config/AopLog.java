package cn.kj0901.tms.base.config;

import com.alibaba.fastjson.JSONArray;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * KJ200901
 *
 * @Description : 统一日志处理
 * @Author : Aedes
 * @Date: 2021/4/1 14:48
 */
@Component
@Aspect
public class AopLog {
    Logger log = LoggerFactory.getLogger(AopLog.class);

    //创建切点
    @Pointcut("execution(public * cn.kj0901.tms.*.controller.*.*(..))")
    private void controllerAspect(){

    }

    //处理进入方法的切面
    @Before(value = "controllerAspect()")
    public void methodBefore(JoinPoint joinPoint){
        ServletRequestAttributes rqa = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = rqa.getRequest();

        //打印请求内容
        log.info("========================请求信息====================");
        log.info("请求地址："+request.getRequestURL());
        log.info("请求方式："+request.getMethod());
        log.info("处理类方法："+joinPoint.getSignature());
        log.info("请求参数："+ Arrays.toString(joinPoint.getArgs()));
        log.info("访问的ip："+request.getLocalAddr());
        log.info("========================请求信息结束====================");
    }

    //处理离开方法的切面
    @AfterReturning(returning = "o" ,pointcut = "controllerAspect()")
    public void methodAfterReturing(Object o){
        log.info("========================返回信息====================");
        log.info("返回信息为："+ JSONArray.toJSONString(o));
        log.info("========================返回信息结束====================");
    }

}

