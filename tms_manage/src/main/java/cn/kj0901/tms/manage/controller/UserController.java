package cn.kj0901.tms.manage.controller;


import cn.hutool.core.util.IdUtil;
import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.dao.UserDao;
import cn.kj0901.tms.base.entity.User;
import cn.kj0901.tms.manage.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("demo")
    public ResultJson demo(@RequestParam Map<String,Object> parMap){
        //参数的合法校验
        if(parMap.get("userType")==null || "".equals(parMap.get("userType"))){
            return ResultJson.err(201,"userType不能为空");
        }
        Integer pageNum = 1;
        Integer pageSize = 10;
        if(parMap.get("pageNum")!=null && !"".equals(parMap.get("pageNum"))){
            pageNum = Integer.valueOf(parMap.get("pageNum").toString());
        }
        if(parMap.get("PageSize")!=null && !"".equals(parMap.get("PageSize"))){
            pageSize = Integer.valueOf(parMap.get("PageSize").toString());
        }

        //通过 pageHelper 开启分页
        PageHelper.startPage(pageNum,pageSize);

        //结果的整合
        List<User> userList = userService.getUserList(parMap);

        //创建pageInfo，将结果集传入
        PageInfo<User> pageInfo = new PageInfo(userList);

        return ResultJson.ok(pageInfo);
    }
}

