package cn.kj0901.tms.store.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.dao.GoodsLockDao;
import cn.kj0901.tms.base.dao.NeedDao;
import cn.kj0901.tms.base.dao.NeedGoodsDao;
import cn.kj0901.tms.base.entity.GoodsLock;
import cn.kj0901.tms.base.entity.Need;
import cn.kj0901.tms.base.entity.NeedGoods;
import cn.kj0901.tms.store.service.NeedService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * 门店需求单 服务实现类
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
@Service
public class NeedServiceImpl extends ServiceImpl<NeedDao, Need> implements NeedService {

    @Resource
    NeedDao needDao;

    @Resource
    GoodsLockDao goodsLockDao;

    @Resource
    NeedGoodsDao needGoodsDao;

    @Override
    @Transactional
    public ResultJson addInfo(Map<String, Object> parMap) {
        //查询临时表信息
        QueryWrapper<GoodsLock> qw = new QueryWrapper<>();
        qw.eq("store_id",parMap.get("storeId"));
        List<GoodsLock> goodsLockList = goodsLockDao.selectList(qw);
        if(goodsLockList==null||goodsLockList.size()==0){
            return ResultJson.err(203,"没有要发起的需求");
        }

        //添加需求单
        LocalDateTime now = LocalDateTime.now();

        Need need = BeanUtil.fillBeanWithMapIgnoreCase(parMap,new Need(),false);
        need.setId(IdUtil.simpleUUID());
        need.setCreateTime(now);
        if(needDao.insert(need)==0){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultJson.err(203,"新增需求单失败");
        }

        //创建需求商品单
        List<NeedGoods> nGList = new ArrayList<>();
        //AtomicInteger ordinal = new AtomicInteger(1);
        //java8 集合对象.forEach
        goodsLockList.forEach((goodsLock)->{
            NeedGoods ng = new NeedGoods();
            ng.setId(IdUtil.simpleUUID());
            ng.setCreateTime(now);
            ng.setNeedId(need.getId());
            ng.setGoodsId(goodsLock.getGoodsId());
            ng.setGoodsNum(goodsLock.getLockNum());
            nGList.add(ng);
            //ordinal.addAndGet(ng.getGoodsNum());
        });

        //添加商品需求单
        if(needGoodsDao.insertList(nGList)==0){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultJson.err(203,"新增需求商品单失败");
        }

        //清空临时信息
        if(goodsLockDao.deleteList(goodsLockList)==0){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultJson.err(203,"新增需求商品单失败");
        }

        return ResultJson.ok();
    }
}
