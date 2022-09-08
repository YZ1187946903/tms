package cn.kj0901.tms.driver.service.impl;

import cn.kj0901.tms.base.entity.User;
import cn.kj0901.tms.base.dao.UserDao;
import cn.kj0901.tms.driver.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author kj0901
 * @since 2021-04-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}
