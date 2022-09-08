package cn.kj0901.tms.driver.service;

import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.entity.TransportReplace;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 运输单交接处理 服务类
 * </p>
 *
 * @author kj0901
 * @since 2021-04-12
 */
public interface TransportReplaceService extends IService<TransportReplace> {
    /**
     * 方法介绍
     *     异常处理
     * @author Aedes
     * @date 2021/4/12 15:51
     * @return cn.kj0901.tms.base.config.ResultJson
     * @throws
     */
    ResultJson endReplace(Map<String, Object> parMap);
}
