package cn.kj0901.tms.manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.dao.UserDao;
import cn.kj0901.tms.base.entity.Driver;
import cn.kj0901.tms.base.dao.DriverDao;
import cn.kj0901.tms.base.entity.Store;
import cn.kj0901.tms.base.entity.User;
import cn.kj0901.tms.base.entity.Warehouse;
import cn.kj0901.tms.base.entity.dto.DriverUserDTO;
import cn.kj0901.tms.base.util.MD5Util;
import cn.kj0901.tms.manage.service.DriverService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
    @Resource
    UserDao userDao;

    @Resource
    DriverDao driverDao;

    @Override
    @Transactional
    public ResultJson addInfo(Map<String, Object> parMap) {

        //查询当前用户是否存在
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("mobile",parMap.get("mobile"));
        List<User> list = userDao.selectList(qw);
        if(list.size()>0){
            return ResultJson.err(203,"用户已存在");
        }

        //创建uuid和时间
        String id = IdUtil.simpleUUID(); //没有-的uuid
        LocalDateTime now = LocalDateTime.now();  //1.8新内容

        //创建两个实体类
        User user = new User();
        user.setId(id);
        user.setCreateTime(now);
        user.setUserType(1); //用户类型为1
        user.setMobile(parMap.get("mobile").toString());
        user.setPwd(MD5Util.enc("123456"));

        Driver driver = BeanUtil.fillBeanWithMapIgnoreCase(parMap,new Driver(),false);

        driver.setId(id);
        driver.setCreateTime(now);
        driver.setUpdateTime(now);

        //完成插入操作
        if(driverDao.insert(driver)!=1){
            log.info("司机插入失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultJson.err(203,"司机插入失败");
        }

        if(userDao.insert(user)!=1){
            log.info("用户插入失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultJson.err(203,"用户插入失败");
        }

        return ResultJson.ok();
    }

    @Override
    public List<DriverUserDTO> getDriverList(Map<String, Object> parMap) {

        //查询
        List<DriverUserDTO> driverUserDTOList = driverDao.selectDriverAndUserList(parMap);

        return driverUserDTOList;
    }
}
