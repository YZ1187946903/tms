package cn.kj0901.tms.manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.entity.Store;
import cn.kj0901.tms.base.entity.Warehouse;
import cn.kj0901.tms.base.dao.WarehouseDao;
import cn.kj0901.tms.manage.service.WarehouseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 仓库 服务实现类
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
@Service
@Slf4j
public class WarehouseServiceImpl extends ServiceImpl<WarehouseDao, Warehouse> implements WarehouseService {
    @Resource
    WarehouseDao warehouseDao;

    @Override
    public ResultJson addInfo(Map<String, Object> parMap) {
        String id = IdUtil.simpleUUID();
        LocalDateTime now = LocalDateTime.now();

        Warehouse warehouse = BeanUtil.fillBeanWithMapIgnoreCase(parMap,new Warehouse(),false);
        warehouse.setId(id);
        warehouse.setCreateTime(now);
        warehouse.setUpdateTime(now);
        if(warehouseDao.insert(warehouse)!=1){
            log.info("仓库添加失败");
            return ResultJson.err(203,"仓库添加失败");
        }
        return ResultJson.ok();
    }

    @Override
    public List<Warehouse> getWarehouseList(Map<String, Object> parMap) {

        QueryWrapper<Warehouse> qw = new QueryWrapper<>();

        qw.eq("del_state",0);
        if(parMap.get("wareMobile")!=null){
            qw.like("ware_mobile",parMap.get("wareMobile"));
        }

        if(parMap.get("name")!=null) {
            qw.like("name", parMap.get("name"));
        }
        qw.orderByDesc("create_time");

        List<Warehouse> warehouseList = warehouseDao.selectList(qw);

        return warehouseList;
    }
}
