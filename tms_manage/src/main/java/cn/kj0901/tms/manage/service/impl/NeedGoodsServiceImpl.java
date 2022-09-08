package cn.kj0901.tms.manage.service.impl;

import cn.kj0901.tms.base.entity.NeedGoods;
import cn.kj0901.tms.base.dao.NeedGoodsDao;
import cn.kj0901.tms.base.entity.dto.NeedGoodsInfoDTO;
import cn.kj0901.tms.manage.service.NeedGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<NeedGoodsInfoDTO> getListByNeedId(Map<String, Object> parMap) {

        return needGoodsDao.getListByNeedId(parMap);
    }

    @Override
    public List<NeedGoodsInfoDTO> getInfoList(Map<String, Object> parMap) {
        parMap.put("status",0);
        parMap.put("needStatus",1);
        parMap.put("tempStatus",0);

        return needGoodsDao.getListByNeedId(parMap);
    }

    @Override
    public List<NeedGoodsInfoDTO> infolistByTranspot(Map<String, Object> parMap) {

        return needGoodsDao.getListByNeedId(parMap);
    }
}
