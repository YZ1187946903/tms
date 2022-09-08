package cn.kj0901.tms.store.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.dao.GoodsDao;
import cn.kj0901.tms.base.dao.GoodsLockDao;
import cn.kj0901.tms.base.entity.GoodsLock;
import cn.kj0901.tms.store.service.GoodsLockService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        QueryWrapper<GoodsLock> qw = new QueryWrapper();
        qw.eq("store_id",goodsLock.getStoreId()).eq("goods_id",goodsLock.getGoodsId());
        List<GoodsLock> goodsLockList = goodsLockDao.selectList(qw);

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
            /*
             * 处理回滚的两种方式
             * 1.自己触发一个运行时异常
             * 2.通过手动触发回滚操作
             */
            //new RuntimeException();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultJson.err(203,"商品锁定失败");
        }

        //修改库存信息
        Map<String,Object> downMap = new HashMap<String, Object>();
        downMap.put("id",goodsLock.getGoodsId());
        downMap.put("lockNum",goodsLock.getLockNum());
        if(goodsDao.downStockByid(downMap)==0){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultJson.err(203,"商品库存处理失败");
        }

        return ResultJson.ok();
    }


    @Override
    @Transactional
    public ResultJson lockTimeOut() {
        //查询超时锁定信息，超时时间30分钟
        List<GoodsLock> goodsLockList = goodsLockDao.getTimeOutList(30*60);
        if(goodsLockList.size()==0){
            return ResultJson.ok(0);
        }


        /**
         *  10
         *  goods1 10  storeid 1
         *  goods1 20  storeid 2
         *  googs1 30  storeid 3
         *  3 goods2
         *  goods1 60
         */
        //java8-groupby分组整理数据
        // k goodsId v数量
        Map<String,Integer> goodsLockNumMap =
                goodsLockList.stream().collect(
                        Collectors.groupingBy(
                                GoodsLock::getGoodsId,  //使用类调用实例对象中的方法
                                Collectors.summingInt(GoodsLock::getLockNum)));

        //obj1,obj2,obj3
//        Map<String,Long> goodsCounMap =
//        goodsLockList.stream().collect(Collectors.groupingBy(
//                GoodsLock::getStoreId,Collectors.counting()
//        ));
        //

        //过滤storeid为123
//        goodsLockList.stream().filter(goodsLock -> {
//            return "123".equals(goodsLock.getStoreId());
//        }).collect(Collectors.toList()).forEach(
//                item->{
//                    System.out.println(item.getGoodsId());
//                }
//        );


        //释放锁定库存
        goodsLockNumMap.forEach((id,lockNum)->
                goodsDao.upGoodsStockById(id,lockNum));

        //删除锁定信息
        if(goodsLockDao.deleteList(goodsLockList)==goodsLockList.size()){
            return ResultJson.ok(goodsLockList.size());
        }

        return ResultJson.err(203,"删除锁定异常");
    }


}
