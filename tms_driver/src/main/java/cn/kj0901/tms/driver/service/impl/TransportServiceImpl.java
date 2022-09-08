package cn.kj0901.tms.driver.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.entity.Need;
import cn.kj0901.tms.base.entity.Transport;
import cn.kj0901.tms.base.dao.TransportDao;
import cn.kj0901.tms.driver.service.TransportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 * 运输单 服务实现类
 * </p>
 *
 * @author kj0901
 * @since 2021-04-12
 */
@Service
public class TransportServiceImpl extends ServiceImpl<TransportDao, Transport> implements TransportService {

    @Resource
    TransportDao transportDao;

    @Override
    public ResultJson reportExcption(Map<String, Object> parMap) {
        //组装用来update的entity
        Transport transport = BeanUtil.fillBeanWithMapIgnoreCase(parMap,new Transport(),false);
        transport.setExcepTime(LocalDateTime.now());
        transport.setStatus(4);

        //完成修改操作
        if(transportDao.updateById(transport)!=1){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultJson.err(203,"异常上报失败");
        }

        return ResultJson.ok();
    }
}
