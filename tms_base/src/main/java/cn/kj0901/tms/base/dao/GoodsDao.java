package cn.kj0901.tms.base.dao;

import cn.kj0901.tms.base.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 * 商品 Mapper 接口
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
public interface GoodsDao extends BaseMapper<Goods> {

    int updateGoodsStockByid(Map<String,Object> parMap);

    int downStockByid(Map<String,Object> parMap);

    int upGoodsStockById(String id,Integer lockNum);

}
