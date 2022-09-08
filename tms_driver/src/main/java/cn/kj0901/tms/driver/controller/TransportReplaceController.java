package cn.kj0901.tms.driver.controller;


import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.util.ParamUtil;
import cn.kj0901.tms.driver.service.TransportReplaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.SendResult;
import java.util.Map;

/**
 * <p>
 * 运输单交接处理 前端控制器
 * </p>
 *
 * @author kj0901
 * @since 2021-04-12
 */
@RestController
@RequestMapping("/transportReplace")
public class TransportReplaceController {
    @Autowired
    TransportReplaceService transportReplaceService;

    @PostMapping("endReplace")
    ResultJson endReplace(@RequestParam Map<String, Object> parMap){
        String[] pars = {"id","lon","lat"};
        ResultJson resultJson = ParamUtil.checkParam(parMap, pars);
        if (resultJson.getCode() != 200) {
            return resultJson;
        }

        return transportReplaceService.endReplace(parMap);
    }
}

