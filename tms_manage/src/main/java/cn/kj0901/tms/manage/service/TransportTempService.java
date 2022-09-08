package cn.kj0901.tms.manage.service;

import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.entity.TransportTemp;
import cn.kj0901.tms.base.entity.dto.TranTempDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 运输临时单 服务类
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
public interface TransportTempService extends IService<TransportTemp> {
    ResultJson add(Map<String, Object> parMap);

    ResultJson delAll(Map<String, Object> parMap);

    List<TranTempDTO> getList(Map<String, Object> parMap);


}
