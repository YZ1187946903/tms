package cn.kj0901.tms.manage.controller;


import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.util.RedisUtil;
import cn.kj0901.tms.manage.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 管理员 前端控制器
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService accountService;

    @Autowired
    RedisUtil redisUtil;


    @PostMapping("login")
    public ResultJson login(@RequestParam Map<String,Object> praMap) {
        if(praMap.get("userName")==null){
            return ResultJson.err(201,"密码不能为空");
        }
        if(praMap.get("password")==null){
            return ResultJson.err(201,"密码不能为空");
        }
        return accountService.login(praMap);
    }

}

