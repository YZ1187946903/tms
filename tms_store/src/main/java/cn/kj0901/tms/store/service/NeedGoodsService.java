package cn.kj0901.tms.store.service;

import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.entity.NeedGoods;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 需求商品单 服务类
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
public interface NeedGoodsService extends IService<NeedGoods> {
    ResultJson endNeedGoodsByIds(String [] ids);
}
