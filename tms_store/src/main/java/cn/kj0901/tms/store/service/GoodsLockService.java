package cn.kj0901.tms.store.service;

import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.entity.GoodsLock;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 商品锁定 服务类
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
public interface GoodsLockService extends IService<GoodsLock> {
    /**
     * 方法介绍
     *    添加商品信息
     * @author Aedes
     * @date 2021/4/8 16:10
     * @return cn.kj0901.tms.base.config.ResultJson
     * @throws
     */
    ResultJson addGoods(Map<String, Object> parMap);

    ResultJson lockTimeOut();


}
