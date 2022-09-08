package cn.kj0901.tms.driver.service.impl;

import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.dao.NeedGoodsDao;
import cn.kj0901.tms.base.dao.TransportDao;
import cn.kj0901.tms.base.entity.Need;
import cn.kj0901.tms.base.entity.NeedGoods;
import cn.kj0901.tms.base.entity.Transport;
import cn.kj0901.tms.base.entity.TransportReplace;
import cn.kj0901.tms.base.dao.TransportReplaceDao;
import cn.kj0901.tms.driver.service.TransportReplaceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 运输单交接处理 服务实现类
 * </p>
 *
 * @author kj0901
 * @since 2021-04-12
 */
@Service
public class TransportReplaceServiceImpl extends ServiceImpl<TransportReplaceDao, TransportReplace> implements TransportReplaceService {

    @Resource
    TransportReplaceDao transportReplaceDao;

    @Resource
    TransportDao transportDao;

    @Resource
    NeedGoodsDao needGoodsDao;

    @Override
    @Transactional
    public ResultJson endReplace(Map<String, Object> parMap) {
        //通过交接单id查询交接单信息
        TransportReplace transportReplace = transportReplaceDao.selectById(parMap.get("id").toString());
        if(transportReplace==null){
            return ResultJson.err(203,"交接单信息异常");
        }

        //通过运输单id和商品单状态查询商品单
        QueryWrapper<NeedGoods> qw = new QueryWrapper<NeedGoods>();
        qw.eq("transport_id",transportReplace.getTransportId());
        qw.lt("status",3);
        List<NeedGoods> needGoodsList = needGoodsDao.selectList(qw);

        //修改运输单信息
        Transport transport = new Transport();
        transport.setId(transportReplace.getTransportId());
        transport.setDriverId(transportReplace.getDriverId());
        transport.setTruckId(transportReplace.getTruckId());
        transport.setStatus(0);
        transport.setExcepEndTime(LocalDateTime.now());
        if(transportDao.updateById(transport)==0){
            return ResultJson.err(203,"车辆更换失败");
        }

        //修改交接单
        transportReplace.setEndTime(LocalDateTime.now());
        transportReplace.setStatus(1);
        transportReplace.setLat(new BigDecimal(parMap.get("lat").toString()));
        transportReplace.setLon(new BigDecimal(parMap.get("lon").toString()));

        if(transportReplaceDao.updateById(transportReplace)!=1){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultJson.err(203,"交接单处理失败");
        }

        //批量修改商品单车辆id，司机id
        if(needGoodsList.size()==0){
            return ResultJson.ok();
        }
        needGoodsList.forEach( needGoods -> {
            needGoods.setDriverId(transportReplace.getDriverId());
            needGoods.setTruckId(transportReplace.getTruckId());
        });

        if (needGoodsDao.updateByNgList(needGoodsList)!=needGoodsList.size()){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultJson.err(203,"商品单处理失败");
        }

        return ResultJson.ok();
    }
}
