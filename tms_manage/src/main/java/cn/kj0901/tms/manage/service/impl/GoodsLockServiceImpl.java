package cn.kj0901.tms.manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.dao.GoodsDao;
import cn.kj0901.tms.base.entity.GoodsLock;
import cn.kj0901.tms.base.dao.GoodsLockDao;
import cn.kj0901.tms.base.entity.Truck;
import cn.kj0901.tms.manage.service.GoodsLockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品锁定 服务实现类
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
@Service
public class GoodsLockServiceImpl extends ServiceImpl<GoodsLockDao, GoodsLock> implements GoodsLockService {

    @Resource
    GoodsLockDao goodsLockDao;

    @Resource
    GoodsDao goodsDao;

    @Override
    @Transactional
    public ResultJson addGoods(Map<String, Object> parMap) {
        //生成对象
        LocalDateTime now = LocalDateTime.now();
        GoodsLock goodsLock = BeanUtil.fillBeanWithMapIgnoreCase(parMap,new GoodsLock(),false);

        //查询锁定数据
        List<GoodsLock> goodsLockList = goodsLockDao.selectByMap(parMap);

        //根据查询结果添加或修改锁定数据
        int flag =0;
        if(goodsLockList==null||goodsLockList.size()==0){
            goodsLock.setId(IdUtil.simpleUUID());
            goodsLock.setCreateTime(now);
            flag = goodsLockDao.insert(goodsLock);
        }else{
            GoodsLock goodsLock1 = goodsLockList.get(0);
            goodsLock1.setLockNum(goodsLock1.getLockNum()+goodsLock.getLockNum());
            flag = goodsLockDao.updateById(goodsLock1);
        }

        //判断修改结果
        if(flag==0){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultJson.err(203,"商品锁定失败");
        }

        //修改库存信息
        Map<String,Object> downMap = new HashMap<String, Object>();
        downMap.put("id",goodsLockList.get(0).getId());
        downMap.put("lockNum",goodsLock.getLockNum());
        if(goodsDao.downStockByid(downMap)==0){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultJson.err(203,"商品锁定失败");
        }


        return ResultJson.ok();
    }
}
