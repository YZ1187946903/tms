package cn.kj0901.tms.manage.service.impl;

import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.entity.Admin;
import cn.kj0901.tms.base.dao.AdminDao;
import cn.kj0901.tms.base.util.MD5Util;
import cn.kj0901.tms.base.util.RedisUtil;
import cn.kj0901.tms.base.util.TokenUtil;
import cn.kj0901.tms.manage.service.AdminService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    TokenUtil tokenUtil;

    @Resource
    AdminDao adminDao;

    @Override
    public ResultJson login(Map<String,Object> parMap) {

        QueryWrapper<Admin> qw = new QueryWrapper<>();
        qw.eq("user_name",parMap.get("userName"));
        qw.eq("del_state",0);

        //查询用户信息
        List<Admin> userList =  adminDao.selectList(qw);
        if(userList==null||userList.size()==0){
            return ResultJson.err(203,"用户不存在,请在数据库添加");
        }
        String pwd = MD5Util.enc(parMap.get("password").toString());
        if(!userList.get(0).getPassword().equals(pwd)){
            return ResultJson.err(203,"密码错误");
        }

        //存放登录用户名称并获取token返回给用户
        String userJson = JSONObject.toJSONString(userList.get(0));
        String token = tokenUtil.getToken(userJson);
        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        map.put("admin",userList.get(0));
        return ResultJson.ok(map);
    }
}
