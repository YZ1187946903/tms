package cn.kj0901.tms.base.dao;

import cn.kj0901.tms.base.entity.TransportTemp;
import cn.kj0901.tms.base.entity.dto.TranTempDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 运输临时单 Mapper 接口
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
public interface TransportTempDao extends BaseMapper<TransportTemp> {
    int addByList(List<TransportTemp> tempList);

    List<TranTempDTO> getTranTempList(Map<String, Object> parMap);
}
