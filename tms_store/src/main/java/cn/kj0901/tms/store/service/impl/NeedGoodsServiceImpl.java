package cn.kj0901.tms.store.service.impl;

import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.dao.NeedDao;
import cn.kj0901.tms.base.dao.NeedGoodsDao;
import cn.kj0901.tms.base.dao.TransportDao;
import cn.kj0901.tms.base.entity.NeedGoods;
import cn.kj0901.tms.store.service.NeedGoodsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 需求商品单 服务实现类
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
@Service
public class NeedGoodsServiceImpl extends ServiceImpl<NeedGoodsDao, NeedGoods> implements NeedGoodsService {

    @Resource
    NeedGoodsDao needGoodsDao;

    @Resource
    NeedDao needDao;

    @Resource
    TransportDao transportDao;

    @Override
    @Transactional
    public ResultJson endNeedGoodsByIds(String[] ids) {

        //查询所有需求商品单，并判断
        List<NeedGoods> needGoodsList = needGoodsDao.selectByIds(Arrays.asList(ids));
        for (NeedGoods ng:needGoodsList) {
            if(ng.getStatus()!=3){
                return ResultJson.err(203,"有未送达的运单");
            }
        }

        //批量完成收货
        if(needGoodsDao.endNgByList(Arrays.asList(ids))==0){
            return ResultJson.err(203,"收货失败");
        }

        //获取送达的运输单id
        Set<String> tranIdSet = new HashSet<>();
        needGoodsList.forEach(ng->{
            tranIdSet.add(ng.getTransportId());
        });
        //查询并确认需要完成的运输单id
        List<String> tranIdList = new ArrayList<>();
        tranIdSet.forEach(needId->{
            QueryWrapper<NeedGoods> qw= new QueryWrapper<>();
            qw.eq("transport_id",needId).ne("status",4);
            if(needGoodsDao.selectList(qw).size()==0){
                tranIdList.add(needId);
            }
        });

        //修改状态
        if(tranIdList.size()!=0){
            if(transportDao.endStatusByList(tranIdList)==0){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ResultJson.err(203,"运单处理失败");
            }
        }


        //获取送达的需求单id
        Set<String> needIdSet = new HashSet<>();
        needGoodsList.forEach(ng->{
            needIdSet.add(ng.getNeedId());
        });

        //查询并确认需要完成的需求单id
        List<String> endIdList = new ArrayList<>();
        needIdSet.forEach(needId->{
            QueryWrapper<NeedGoods> qw= new QueryWrapper<>();
            qw.eq("need_id",needId).ne("status",4);
            if(needGoodsDao.selectList(qw).size()==0){
                endIdList.add(needId);
            }
        });

        if(endIdList.size()==0){
            return ResultJson.ok();
        }

        //对需要完成的需求单完成状态修改
        if(needDao.endStatusByList(endIdList)==0){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultJson.err(203,"需求单处理失败");
        }




        return ResultJson.ok();
    }
}
