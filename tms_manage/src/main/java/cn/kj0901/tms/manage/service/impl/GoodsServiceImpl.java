package cn.kj0901.tms.manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.entity.Goods;
import cn.kj0901.tms.base.dao.GoodsDao;
import cn.kj0901.tms.base.entity.Warehouse;
import cn.kj0901.tms.manage.service.GoodsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品 服务实现类
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
@Service
@Slf4j
@Transactional
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, Goods> implements GoodsService {
    @Resource
    GoodsDao goodsDao;

    @Override
    public ResultJson addInfo(Map<String, Object> parMap) {
        String id = IdUtil.simpleUUID();
        LocalDateTime now = LocalDateTime.now();

        Goods goods = BeanUtil.fillBeanWithMapIgnoreCase(parMap,new Goods(),false);
        goods.setId(id);
        goods.setCreateTime(now);
        goods.setUpdateTime(now);


        if(goodsDao.insert(goods)!=1){
            log.info("商品添加失败");
            return ResultJson.err(203,"商品添加失败");
        }
        return ResultJson.ok();
    }

    @Override
    public List<Goods> getGoodsList(Map<String, Object> parMap) {
        QueryWrapper<Goods> qw = new QueryWrapper<>();

        qw.eq("del_state",0).eq("warehouse_id",parMap.get("warehouseId"));
        if(parMap.get("goodsName")!=null){
            qw.like("goods_name",parMap.get("goodsName"));
        }

        if(parMap.get("goodsNum")!=null) {
            qw.like("goods_num", parMap.get("goodsNum"));
        }
        qw.orderByAsc("goods_stock").orderByDesc("create_time");

        List<Goods> warehouseList = goodsDao.selectList(qw);

        return warehouseList;
    }

    @Override
    public ResultJson AddGoodsStock(Map<String, Object> parMap) {

        if(goodsDao.updateGoodsStockByid(parMap)!=1){
            log.info("库存调整失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultJson.err(203,"库存调整失败");
        }

        return ResultJson.ok();
    }
}
