package cn.kj0901.tms.manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.entity.Store;
import cn.kj0901.tms.base.entity.Truck;
import cn.kj0901.tms.base.dao.TruckDao;
import cn.kj0901.tms.base.entity.dto.TruckInfoDTO;
import cn.kj0901.tms.manage.service.TruckService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    @Resource
    TruckDao truckDao;

    @Override
    public List<TruckInfoDTO> getTruckList(Map<String, Object> parMap) {

        //查询
        List<TruckInfoDTO> truckList = truckDao.selectTruckInfoList(parMap);

        return truckList;
    }



    @Override
    public ResultJson updateDriverById(Map<String, Object> parMap) {
        //创建时间
        LocalDateTime now = LocalDateTime.now();

        //通过map生成实体
        Truck truck = BeanUtil.fillBeanWithMapIgnoreCase(parMap,new Truck(),false);
        truck.setUpdateTime(now);

        //完成修改
        if(truckDao.updateById(truck)==1){
            return ResultJson.ok();
        }
        return ResultJson.err(203,"修改失败");
    }
}
