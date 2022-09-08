package cn.kj0901.tms.manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.dao.GoodsLockDao;
import cn.kj0901.tms.base.dao.NeedGoodsDao;
import cn.kj0901.tms.base.entity.GoodsLock;
import cn.kj0901.tms.base.entity.Need;
import cn.kj0901.tms.base.dao.NeedDao;
import cn.kj0901.tms.base.entity.NeedGoods;
import cn.kj0901.tms.base.entity.Store;
import cn.kj0901.tms.base.entity.dto.NeedInfoDTO;
import cn.kj0901.tms.manage.service.NeedService;
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
    public List<NeedInfoDTO> getNeedInfoList(Map<String, Object> parMap) {
        return needDao.getNeedInfoList(parMap);
    }

    @Override
    public NeedInfoDTO getNeedInfoById(Map<String, Object> parMap) {
        List<NeedInfoDTO> needInfoDTOS = needDao.getNeedInfoList(parMap);

        return needInfoDTOS.get(0);
    }

    @Override
    public ResultJson checkNeed(Map<String, Object> parMap) {
        Need need = BeanUtil.fillBeanWithMapIgnoreCase(parMap,new Need(),false);
        if(need.getStatus()==3&&need.getRejectInfo()==null){
            return ResultJson.err(201,"请填写驳回原因");
        }
        if(needDao.updateById(need)!=1){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultJson.err(203,"状态修改失败");
        }

        return ResultJson.ok();
    }
}
