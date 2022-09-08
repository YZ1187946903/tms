package cn.kj0901.tms.manage.service;

import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.entity.Need;
import cn.kj0901.tms.base.entity.dto.NeedInfoDTO;
import cn.kj0901.tms.base.entity.dto.TransportInfoDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
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


    List<NeedInfoDTO> getNeedInfoList(Map<String, Object> parMap);

    NeedInfoDTO getNeedInfoById(Map<String, Object> parMap);

    ResultJson checkNeed(Map<String, Object> parMap);

}
