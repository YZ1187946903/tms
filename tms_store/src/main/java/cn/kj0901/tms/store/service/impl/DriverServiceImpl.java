package cn.kj0901.tms.store.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.dao.DriverDao;
import cn.kj0901.tms.base.dao.UserDao;
import cn.kj0901.tms.base.entity.Driver;
import cn.kj0901.tms.base.entity.User;
import cn.kj0901.tms.base.entity.dto.DriverUserDTO;
import cn.kj0901.tms.base.util.MD5Util;
import cn.kj0901.tms.store.service.DriverService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 司机 服务实现类
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
@Slf4j
@Service
public class DriverServiceImpl extends ServiceImpl<DriverDao, Driver> implements DriverService {

}
