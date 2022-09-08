package cn.kj0901.tms.driver.controller;


import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.util.ParamUtil;
import cn.kj0901.tms.driver.service.NeedGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 * 需求商品单 前端控制器
 * </p>
 *
 * @author kj0901
 * @since 2021-04-12
 */
@RestController
@RequestMapping("/needGoods")
public class NeedGoodsController {

    @Autowired
    NeedGoodsService needGoodsService;
    /**
    * 方法介绍
    *      配送处理
    * @author Aedes
    * @date 2021/4/12 15:58 
    * @return cn.kj0901.tms.base.config.ResultJson  
    * @throws   
    */
    @PostMapping("delivery")
    public ResultJson delivery(@RequestParam Map<String, Object> parMap){
        String[] pars = {"ids"};
        ResultJson resultJson = ParamUtil.checkParam(parMap, pars);
        if (resultJson.getCode() != 200) {
            return resultJson;
        }

        String ids = parMap.get("ids").toString();

        return needGoodsService.delivery(Arrays.asList(ids.split(",")));
    }
    /**
    * 方法介绍
    *      送达处理
    * @author Aedes
    * @date 2021/4/12 15:59
    * @return cn.kj0901.tms.base.config.ResultJson  
    * @throws   
    */
    @PostMapping("send")
    public ResultJson send(@RequestParam  Map<String, Object> parMap){
        String[] pars = {"ids"};
        ResultJson resultJson = ParamUtil.checkParam(parMap, pars);
        if (resultJson.getCode() != 200) {
            return resultJson;
        }

        String ids = parMap.get("ids").toString();

        return needGoodsService.send(Arrays.asList(ids.split(",")));
    }

}

