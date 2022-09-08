package cn.kj0901.tms.store.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * KJ200901
 *
 * @Description :
 * @Author : Aedes
 * @Date: 2021/4/13 15:02
 */
@Configuration
public class QuartzSchedule {

    //构建一个JobDetail
    @Bean
    public JobDetail goodsLockJob(){
        return JobBuilder.newJob(GoodsLockJob.class).storeDurably().build();
    }

    @Bean
    public Trigger goodsLockTrigger(){
        //创建调度器-按照模板
        SimpleScheduleBuilder ssb =
                SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(60*30).repeatForever();
        //创建调度器-按照cron表达式
        CronScheduleBuilder ssb1 =
                CronScheduleBuilder.cronSchedule("0/10 * * * * ?");

        //通过触发器构建者创建触发器,并将调度和任务关联起来
        return TriggerBuilder.newTrigger().forJob(goodsLockJob()).withSchedule(ssb).build();
    }

}

