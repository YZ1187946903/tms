package cn.kj0901.tms.driver.service.impl;

import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.entity.NeedGoods;
import cn.kj0901.tms.base.dao.NeedGoodsDao;
import cn.kj0901.tms.driver.service.NeedGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 需求商品单 服务实现类
 * </p>
 *
 * @author kj0901
 * @since 2021-04-12
 */
@Service
public class NeedGoodsServiceImpl extends ServiceImpl<NeedGoodsDao, NeedGoods> implements NeedGoodsService {

    @Resource
    NeedGoodsDao needGoodsDao;

    @Override
    public ResultJson delivery(List<String> ids) {

        if(needGoodsDao.updateNgByList(ids,2,LocalDateTime.now())==0){
            return ResultJson.err(203,"配货操作失败");
        }

        return ResultJson.ok();
    }

    @Override
    public ResultJson send(List<String> ids) {
        //判断这些id是否都是运输中
        List<NeedGoods> needGoodsList = needGoodsDao.selectByIds(ids);
        for (NeedGoods ng:needGoodsList) {
            if(ng.getStatus()!=2){
                return ResultJson.err(203,"有部分运单无法送达");
            }
        }

        if(needGoodsDao.updateNgByList(ids,3, LocalDateTime.now())==0){
            return ResultJson.err(203,"送达操作失败");
        }
        return ResultJson.ok();
    }
}
