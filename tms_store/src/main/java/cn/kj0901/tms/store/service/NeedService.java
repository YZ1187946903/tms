package cn.kj0901.tms.store.service;

import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.entity.Need;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 门店需求单 服务类
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
public interface NeedService extends IService<Need> {
    /**
    * 方法介绍
    *    需求商品单添加
    * @author Aedes
    * @date 2021/4/9 15:51 
    * @return cn.kj0901.tms.base.config.ResultJson  
    * @throws   
    */
    ResultJson addInfo(Map<String, Object> parMap);
}
