package cn.kj0901.tms.store.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.dao.WarehouseDao;
import cn.kj0901.tms.base.entity.Warehouse;
import cn.kj0901.tms.store.service.WarehouseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 仓库 服务实现类
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
@Service
@Slf4j
public class WarehouseServiceImpl extends ServiceImpl<WarehouseDao, Warehouse> implements WarehouseService {

}
