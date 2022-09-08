package cn.kj0901.tms.manage.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.dao.NeedGoodsDao;
import cn.kj0901.tms.base.entity.TransportTemp;
import cn.kj0901.tms.base.dao.TransportTempDao;
import cn.kj0901.tms.base.entity.dto.TranTempDTO;
import cn.kj0901.tms.manage.service.TransportTempService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 运输临时单 服务实现类
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
@Service
public class TransportTempServiceImpl extends ServiceImpl<TransportTempDao, TransportTemp> implements TransportTempService {

    @Resource
    TransportTempDao tempDao;

    @Resource
    NeedGoodsDao needGoodsDao;

    @Override
    @Transactional
    public ResultJson add(Map<String, Object> parMap) {
        //转换商品需求信息id
        String ids = parMap.get("ids").toString();
        List<String> idList = Arrays.asList(ids.split(","));

        //添加商品id列表到临时运输单表
        List<TransportTemp> tempList = new ArrayList<>();
        idList.forEach(id->{
            TransportTemp temp = new TransportTemp();
            temp.setId(IdUtil.simpleUUID());
            temp.setAdminId(parMap.get("adminId").toString());
            temp.setNeedGoodsId(id);
            tempList.add(temp);
        });
        if(tempDao.addByList(tempList)!=tempList.size()){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultJson.err(203,"临时信息处理失败");
        }

        //修改商品单为temp_status为1
        if(needGoodsDao.updateTempStatusByIdList(idList,1)!=idList.size()) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultJson.err(203, "商品单状态处理失败");
        }

        return ResultJson.ok();
    }

    @Override
    @Transactional
    public ResultJson delAll(Map<String, Object> parMap) {
        //处理查询条件
        QueryWrapper<TransportTemp> qw = new QueryWrapper<>();
        qw.eq("admin_id",parMap.get("adminId"));

        //查询需要修改的列表
        List<TransportTemp> tempList = tempDao.selectList(qw);
        List<String> idList = new ArrayList<>();
        tempList.forEach(temp->{
            idList.add(temp.getNeedGoodsId());
        });

        //修改商品单为temp_status为0
        if(needGoodsDao.updateTempStatusByIdList(idList,0)!=idList.size()) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultJson.err(203, "商品单状态处理失败");
        }

        //删除临时信息
        if(tempDao.delete(qw)!=tempList.size()){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultJson.err(203, "商品信息删除失败");
        }
        return ResultJson.ok();
    }

    @Override
    public List<TranTempDTO> getList(Map<String, Object> parMap) {

        return tempDao.getTranTempList(parMap);
    }
}
