package cn.kj0901.tms.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * KJ200901
 *
 * @Description :
 * @Author : Aedes
 * @Date: 2021/4/8 14:11
 */
@SpringBootApplication
@ComponentScan("cn.kj0901.tms")
public class Common8082 {

    public static void main(String[] args) {
        SpringApplication.run(Common8082.class);
    }

}

