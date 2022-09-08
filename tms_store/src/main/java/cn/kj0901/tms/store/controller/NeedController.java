package cn.kj0901.tms.store.controller;


import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.util.ParamUtil;
import cn.kj0901.tms.store.service.GoodsLockService;
import cn.kj0901.tms.store.service.NeedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 门店需求单 前端控制器
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
@RestController
@RequestMapping("/need")
@Slf4j
public class NeedController {
    @Autowired
    NeedService needService;

    @PostMapping("add")
    public ResultJson add(@RequestParam Map<String, Object> parMap) {

        String[] pars = {"storeId"};
        ResultJson resultJson = ParamUtil.checkParam(parMap, pars);
        if (resultJson.getCode() != 200) {
            return resultJson;
        }

        return needService.addInfo(parMap);

    }


}

