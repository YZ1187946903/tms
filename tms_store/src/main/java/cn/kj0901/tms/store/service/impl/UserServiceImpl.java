package cn.kj0901.tms.store.service.impl;

import cn.kj0901.tms.base.dao.UserDao;
import cn.kj0901.tms.base.entity.User;
import cn.kj0901.tms.store.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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


}
