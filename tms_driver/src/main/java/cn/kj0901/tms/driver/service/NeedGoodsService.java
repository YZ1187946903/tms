package cn.kj0901.tms.driver.service;

import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.entity.NeedGoods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 需求商品单 服务类
 * </p>
 *
 * @author kj0901
 * @since 2021-04-12
 */
public interface NeedGoodsService extends IService<NeedGoods> {
    /**
    * 方法介绍
    *     配货
    * @author Aedes
    * @date 2021/4/12 15:51 
    * @return cn.kj0901.tms.base.config.ResultJson  
    * @throws   
    */
    ResultJson delivery(List<String> ids);

    /**
     * 方法介绍
     *     送达
     * @author Aedes
     * @date 2021/4/12 15:51
     * @return cn.kj0901.tms.base.config.ResultJson
     * @throws
     */
    ResultJson send(List<String> ids);
}
