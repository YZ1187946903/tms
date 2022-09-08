package cn.kj0901.tms.base.dao;

import cn.kj0901.tms.base.entity.NeedGoods;
import cn.kj0901.tms.base.entity.Transport;
import cn.kj0901.tms.base.entity.dto.NeedGoodsInfoDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 需求商品单 Mapper 接口
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
public interface NeedGoodsDao extends BaseMapper<NeedGoods> {
    int insertList(List<NeedGoods> nGList);

    List<NeedGoods> selectByIds(List<String> ids);

    int endNgByList(List<String> ids);

    int updateNgByList(List<String> ids, Integer status, LocalDateTime loadTime);

    int updateByNgList(List<NeedGoods> needGoodsList);

    List<NeedGoodsInfoDTO> getListByNeedId(Map<String, Object> parMap);

    int updateTempStatusByIdList(@Param("ids") List<String> ids, @Param("tempStatus") Integer tempStatus);

    int updateTransportInfoByList(@Param("ids")List<String> ids, @Param("transport") Transport transport);
}
