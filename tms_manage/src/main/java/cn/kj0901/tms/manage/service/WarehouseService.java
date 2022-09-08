package cn.kj0901.tms.manage.service;

import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.entity.Store;
import cn.kj0901.tms.base.entity.Warehouse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 仓库 服务类
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
public interface WarehouseService extends IService<Warehouse> {
    /**
     * 方法介绍
     *    仓库添加
     * @author Aedes
     * @date 2021/4/6 15:07
     * @return cn.kj0901.tms.base.config.ResultJson
     * @throws
     */
    ResultJson addInfo(Map<String,Object> parMap);

    /**
    * 方法介绍
    *   仓库查询
    * @author Aedes
    * @date 2021/4/7 15:30
    * @return java.util.List<cn.kj0901.tms.base.entity.Store>
    * @throws
    */
    List<Warehouse> getWarehouseList(Map<String,Object> parMap);


}
