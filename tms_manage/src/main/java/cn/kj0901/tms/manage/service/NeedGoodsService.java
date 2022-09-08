package cn.kj0901.tms.manage.service;

import cn.kj0901.tms.base.entity.NeedGoods;
import cn.kj0901.tms.base.entity.dto.NeedGoodsInfoDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 需求商品单 服务类
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
public interface NeedGoodsService extends IService<NeedGoods> {
    List<NeedGoodsInfoDTO>  getListByNeedId(Map<String, Object> parMap);

    List<NeedGoodsInfoDTO> getInfoList(Map<String,Object> parMap);

    List<NeedGoodsInfoDTO> infolistByTranspot(Map<String,Object> parMap);
}
