package cn.kj0901.tms.manage.service;

import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 管理员 服务类
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
public interface AdminService extends IService<Admin> {
    ResultJson login(Map<String, Object> parMap);
}
