package cn.kj0901.tms.manage.service.impl;

import cn.kj0901.tms.base.entity.User;
import cn.kj0901.tms.base.dao.UserDao;
import cn.kj0901.tms.manage.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Resource
    UserDao userDao;


    @Override
    public List<User> getUserList(Map<String, Object> parMap) {
        //mybatis-plus的查询条件包装
        QueryWrapper<User> qw = new QueryWrapper<>();

        if(parMap.get("mobile")!=null){
            qw.like("mobile",parMap.get("mobile"));
        }

        //通过QueryWrapper方法完成sql语句的包装
        qw.eq("user_type",parMap.get("userType"))
                .orderByDesc("create_time");

        //查询
        List<User> userList = userDao.selectList(qw);

        return userList;
    }
}
