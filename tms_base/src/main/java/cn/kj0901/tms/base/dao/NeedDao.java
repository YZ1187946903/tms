package cn.kj0901.tms.base.dao;

import cn.kj0901.tms.base.entity.Need;
import cn.kj0901.tms.base.entity.dto.NeedInfoDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 门店需求单 Mapper 接口
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
public interface NeedDao extends BaseMapper<Need> {
    int endStatusByList(List<String> idList);
    List<NeedInfoDTO> getNeedInfoList(Map<String, Object> parMap);

}
