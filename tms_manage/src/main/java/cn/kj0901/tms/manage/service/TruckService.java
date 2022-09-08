package cn.kj0901.tms.manage.service;

import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.entity.Store;
import cn.kj0901.tms.base.entity.Truck;
import cn.kj0901.tms.base.entity.Warehouse;
import cn.kj0901.tms.base.entity.dto.TruckInfoDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 车辆 服务类
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
public interface TruckService extends IService<Truck> {
    /**
     * 方法介绍
     *   获取车辆信息列表
     * @author Aedes
     * @date 2021/4/8 14:11
     * @return java.util.List<cn.kj0901.tms.base.entity.Store>
     * @throws
     */
    List<TruckInfoDTO> getTruckList(Map<String,Object> parMap);

    /**
     * 方法介绍
     *   通过id司机id
     * @author Aedes
     * @date 2021/4/8 15:39
     * @return cn.kj0901.tms.base.config.ResultJson
     * @throws
     */
    ResultJson updateDriverById(Map<String,Object> parMap);


}
