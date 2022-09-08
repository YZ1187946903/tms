package cn.kj0901.tms.store;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Schedules;

/**
 * KJ200901
 *
 * @Description :
 * @Author : Aedes
 * @Date: 2021/4/9 16:25
 */

@SpringBootApplication
@MapperScan("cn.kj0901.tms.base.dao")
@ComponentScan("cn.kj0901.tms")
public class Store8083 {
    public static void main(String[] args) {
        SpringApplication.run(Store8083.class);
    }
}

