package cn.kj0901.tms.store.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.dao.TruckDao;
import cn.kj0901.tms.base.entity.Truck;
import cn.kj0901.tms.base.entity.dto.TruckInfoDTO;
import cn.kj0901.tms.store.service.TruckService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 车辆 服务实现类
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
@Service
public class TruckServiceImpl extends ServiceImpl<TruckDao, Truck> implements TruckService {


}
