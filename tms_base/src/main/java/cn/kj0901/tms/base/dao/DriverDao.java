package cn.kj0901.tms.base.dao;

import cn.kj0901.tms.base.entity.Driver;
import cn.kj0901.tms.base.entity.dto.DriverUserDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 司机 Mapper 接口
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
public interface DriverDao extends BaseMapper<Driver> {

    List<DriverUserDTO> selectDriverAndUserList(Map<String,Object> parMap);

}
