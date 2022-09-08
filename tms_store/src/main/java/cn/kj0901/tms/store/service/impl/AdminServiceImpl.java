package cn.kj0901.tms.store.service.impl;

import cn.kj0901.tms.base.dao.AdminDao;
import cn.kj0901.tms.base.entity.Admin;
import cn.kj0901.tms.store.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员 服务实现类
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminDao, Admin> implements AdminService {

}
