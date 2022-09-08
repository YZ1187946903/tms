package cn.kj0901.tms.base.dao;

import cn.kj0901.tms.base.entity.Transport;
import cn.kj0901.tms.base.entity.dto.TransportInfoDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 运输单 Mapper 接口
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
public interface TransportDao extends BaseMapper<Transport> {
    List<TransportInfoDTO> getInfoDTOList(Map<String, Object> parMap);

    int endStatusByList(List<String> idList);

}
