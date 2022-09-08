package cn.kj0901.tms.driver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * KJ200901
 *
 * @Description :
 * @Author : Aedes
 * @Date: 2021/4/12 15:40
 */
@SpringBootApplication
@MapperScan("cn.kj0901.tms.base.dao")
@ComponentScan("cn.kj0901.tms")
public class Driver8084 {
    public static void main(String[] args) {
        SpringApplication.run(Driver8084.class);
    }
}

