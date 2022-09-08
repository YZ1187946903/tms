package cn.kj0901.tms.manage.service;

import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.entity.Driver;
import cn.kj0901.tms.base.entity.Warehouse;
import cn.kj0901.tms.base.entity.dto.DriverUserDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 司机 服务类
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
public interface DriverService extends IService<Driver> {
    /**
     * 方法介绍
     *    添加信息
     * @author Aedes
     * @date 2021/4/8 16:10 
     * @return cn.kj0901.tms.base.config.ResultJson
     * @throws
     */
    ResultJson addInfo(Map<String,Object> parMap);

    /**
    * 方法介绍
    *     查询详情
    * @author Aedes
    * @date 2021/4/8 16:11 
    * @return java.util.List<cn.kj0901.tms.base.entity.Warehouse>  
    * @throws   
    */
    List<DriverUserDTO> getDriverList(Map<String,Object> parMap);

}
