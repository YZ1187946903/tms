package cn.kj0901.tms.manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.dao.UserDao;
import cn.kj0901.tms.base.entity.Store;
import cn.kj0901.tms.base.dao.StoreDao;
import cn.kj0901.tms.base.entity.User;
import cn.kj0901.tms.base.util.MD5Util;
import cn.kj0901.tms.manage.service.StoreService;
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
 * 门店 服务实现类
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
@Slf4j
@Service
public class StoreServiceImpl extends ServiceImpl<StoreDao, Store> implements StoreService {

    @Resource
    StoreDao storeDao;

    @Resource
    UserDao userDao;

    @Override
    @Transactional
    public ResultJson addStoreAndUser(Map<String,Object> parMap) {
        //创建uuid和时间
        String id = IdUtil.simpleUUID(); //没有-的uuid
        LocalDateTime now = LocalDateTime.now();  //1.8新内容

        //创建两个实体类
        User user = new User();
        user.setId(id);
        user.setCreateTime(now);
        user.setMobile(parMap.get("mobile").toString());
        user.setPwd(MD5Util.enc("123456"));

        /*
            加密
                摘要   处理数据篡改
                对称   传输安全问题
                非对称  传输安全问题
        */

        Store store = new Store();
        store.setId(id);
        store.setStoreMobile(parMap.get("mobile").toString());
        store.setName(parMap.get("name").toString());
        store.setLat(new BigDecimal(parMap.get("lat").toString()));
        store.setLon(new BigDecimal(parMap.get("lon").toString()));
        store.setAddress(parMap.get("address").toString());
        store.setCreateTime(now);
        store.setUpdateTime(now);

        //完成插入操作
        if(storeDao.insert(store)!=1){
            log.info("门店插入失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultJson.err(203,"门店插入失败");
        }

        if(userDao.insert(user)!=1){
            log.info("用户插入失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultJson.err(203,"门店插入失败");
        }

        return ResultJson.ok();
    }

    @Override
    public List<Store> getStoreList(Map<String, Object> parMap) {

        //根据内容拼接查询条件
        QueryWrapper<Store> qw = new QueryWrapper<>();

        qw.eq("del_state",0);
        if(parMap.get("storeMobile")!=null){
            qw.like("store_mobile",parMap.get("storeMobile"));
        }

        if(parMap.get("name")!=null){
            qw.like("name",parMap.get("name"));
        }

        //通过QueryWrapper方法完成sql语句的包装
        qw.orderByDesc("create_time");

        //查询
        List<Store> storeList = storeDao.selectList(qw);

        return storeList;
    }

    @Override
    public ResultJson updateStoreInfoById(Map<String, Object> parMap) {
        //创建时间
        LocalDateTime now = LocalDateTime.now();

        //通过map生成实体
        Store store = BeanUtil.fillBeanWithMapIgnoreCase(parMap,new Store(),false);
        store.setUpdateTime(now);

        //完成修改
        if(storeDao.updateById(store)==1){
            return ResultJson.ok();
        }
        return ResultJson.err(203,"修改失败");
    }

}
