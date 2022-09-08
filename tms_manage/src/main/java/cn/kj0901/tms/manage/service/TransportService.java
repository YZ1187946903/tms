package cn.kj0901.tms.manage.service;

import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.entity.Transport;
import cn.kj0901.tms.base.entity.dto.TransportInfoDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 运输单 服务类
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
public interface TransportService extends IService<Transport> {

    ResultJson add(Map<String, Object> parMap);

    List<TransportInfoDTO> getInfoList(Map<String, Object> parMap);

    ResultJson getInfo(Map<String, Object> parMap);

    ResultJson handleExc(Map<String, Object> parMap);



}
