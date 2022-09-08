package cn.kj0901.tms.base.dao;

import cn.kj0901.tms.base.entity.GoodsLock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 商品锁定 Mapper 接口
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
public interface GoodsLockDao extends BaseMapper<GoodsLock> {

    int deleteList(List<GoodsLock> goodsLockList);

    List<GoodsLock> getTimeOutList(Integer outTime);

}
