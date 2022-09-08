package cn.kj0901.tms.manage.service;

import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.entity.Store;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 门店 服务类
 * </p>
 * @author kj0901
 * @since 2021-04-05
 */
public interface StoreService extends IService<Store> {

    /**
    * 方法介绍
    *   完成用户和门店的添加操作
    * @author Aedes
    * @date 2021/4/6 15:07 
    * @return cn.kj0901.tms.base.config.ResultJson  
    * @throws   
    */
    ResultJson addStoreAndUser(Map<String,Object> parMap);

    /**
    * 方法介绍
    *   获取门店列表
    * @author Aedes
    * @date 2021/4/7 14:11
    * @return java.util.List<cn.kj0901.tms.base.entity.Store>
    * @throws
    */
    List<Store> getStoreList(Map<String,Object> parMap);

    /**
    * 方法介绍
    *   通过id修改门店信息
    * @author Aedes
    * @date 2021/4/6 15:39
    * @return cn.kj0901.tms.base.config.ResultJson
    * @throws
    */
    ResultJson updateStoreInfoById(Map<String,Object> parMap);


}
