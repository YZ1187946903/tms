package cn.kj0901.tms.base.dao;

import cn.kj0901.tms.base.entity.Truck;
import cn.kj0901.tms.base.entity.dto.TruckInfoDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 车辆 Mapper 接口
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
public interface TruckDao extends BaseMapper<Truck> {

    List<TruckInfoDTO> selectTruckInfoList(Map<String, Object> parMap);

}
