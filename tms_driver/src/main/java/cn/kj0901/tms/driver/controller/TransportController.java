package cn.kj0901.tms.driver.controller;


import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.util.ParamUtil;
import cn.kj0901.tms.driver.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 运输单 前端控制器
 * </p>
 *
 * @author kj0901
 * @since 2021-04-12
 */
@RestController
@RequestMapping("/transport")
public class TransportController {

    @Autowired
    TransportService transportService;
    /**
     * 方法介绍
     *     异常上报
     * @author Aedes
     * @date 2021/4/12 15:51
     * @return cn.kj0901.tms.base.config.ResultJson
     * @throws
     */
    @PostMapping("reportExcption")
    public ResultJson reportExcption(@RequestParam  Map<String, Object> parMap){
        String[] pars = {"id","excepInfo","excepLon","excepLat"};
        ResultJson resultJson = ParamUtil.checkParam(parMap, pars);
        if (resultJson.getCode() != 200) {
            return resultJson;
        }

        return transportService.reportExcption(parMap);

    }
}

