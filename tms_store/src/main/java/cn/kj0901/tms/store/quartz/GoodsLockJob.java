package cn.kj0901.tms.store.quartz;

import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.store.service.GoodsLockService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * KJ200901
 *
 * @Description : 处理过期商品锁定
 * @Author : Aedes
 * @Date: 2021/4/13 15:00
 */
@Slf4j
public class GoodsLockJob extends QuartzJobBean {
    @Autowired
    GoodsLockService goodsLockService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
      log.info("===========处理过期商品锁定开启==========");

      ResultJson rj = goodsLockService.lockTimeOut();

      log.info("已处理"+rj.getData()+"条数据");
      log.info("===========处理过期商品锁定关闭==========");

    }


}

