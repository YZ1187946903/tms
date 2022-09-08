package cn.kj0901.tms.store.service;

import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品 服务类
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
public interface GoodsService extends IService<Goods> {
    /**
    * 方法介绍
    *   添加商品
    * @author Aedes
    * @date 2021/4/7 15:44
    * @return cn.kj0901.tms.base.config.ResultJson
    * @throws
    */
    ResultJson addInfo(Map<String, Object> parMap);

    /**
    * 方法介绍
    *   查询商品
    * @author Aedes
    * @date 2021/4/7 15:44
    * @return java.util.List<cn.kj0901.tms.base.entity.Goods>
    * @throws
    */
    List<Goods> getGoodsList(Map<String, Object> parMap);

    /**
     * 方法介绍
     *   添加商品库存
     * @author Aedes
     * @date 2021/4/7 15:44
     * @return java.util.List<cn.kj0901.tms.base.entity.Goods>
     * @throws
     */
    ResultJson AddGoodsStock(Map<String, Object> parMap);
}
