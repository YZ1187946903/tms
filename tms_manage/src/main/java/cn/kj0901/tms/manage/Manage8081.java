package cn.kj0901.tms.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * KJ200901
 *
 * @Description :
 * @Author : Aedes
 * @Date: 2021/4/5 15:41
 */
@SpringBootApplication
@MapperScan("cn.kj0901.tms.base.dao")
@ComponentScan("cn.kj0901.tms")
public class Manage8081 extends SpringBootServletInitializer{ //忽略springboot自带的tomcat
    public static void main(String[] args) {
        SpringApplication.run(Manage8081.class);
    }
}