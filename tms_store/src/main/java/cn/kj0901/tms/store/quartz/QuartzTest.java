package cn.kj0901.tms.store.quartz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * KJ200901
 *
 * @Description : 使用注解方式完成定时任务
 * @Author : Aedes
 * @Date: 2021/4/14 14:15
 */
@Slf4j
//@EnableScheduling
//@Component
public class QuartzTest {

    @Scheduled(cron = "0/5 * * * * ?")
    public void test(){
        log.info("===============================处理过期锁定============");
    }

}

