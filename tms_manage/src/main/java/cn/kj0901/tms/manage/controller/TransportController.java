package cn.kj0901.tms.manage.controller;


import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.entity.Goods;
import cn.kj0901.tms.base.entity.Transport;
import cn.kj0901.tms.base.entity.dto.NeedGoodsInfoDTO;
import cn.kj0901.tms.base.entity.dto.TransportInfoDTO;
import cn.kj0901.tms.base.util.ParamUtil;
import cn.kj0901.tms.manage.service.TransportService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 运输单 前端控制器
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
@RestController
@RequestMapping("/transport")
public class TransportController {

    @Autowired
    TransportService transportService;

    @PostMapping("add")
    public ResultJson add(@RequestParam Map<String, Object> parMap) {

        String[] pars = {"adminId","truckId"};
        ResultJson resultJson = ParamUtil.checkParam(parMap, pars);
        if (resultJson.getCode() != 200) {
            return resultJson;
        }

        return transportService.add(parMap);
    }

    @PostMapping("list")
    public ResultJson list(@RequestParam Map<String, Object> parMap) {

        Integer pageNum = 1;
        Integer pageSize = 10;
        ParamUtil.putPageInfo(parMap, pageNum, pageSize);

        List<TransportInfoDTO> tranList = transportService.getInfoList(parMap);
        PageInfo<TransportInfoDTO> pageInfo = new PageInfo<>(tranList);

        return ResultJson.ok(pageInfo);

    }

    @PostMapping("info")
    public ResultJson info(@RequestParam Map<String, Object> parMap) {

        String[] pars = {"id"};
        ResultJson resultJson = ParamUtil.checkParam(parMap, pars);
        if (resultJson.getCode() != 200) {
            return resultJson;
        }

        return transportService.getInfo(parMap);
    }

    /**
     * 异常处理
     * @param parMap
     * @return
     */
    @PostMapping("handlex")
    public ResultJson handleEx(@RequestParam Map<String, Object> parMap) {

        String[] pars = {"id","truckId"};
        ResultJson resultJson = ParamUtil.checkParam(parMap, pars);
        if (resultJson.getCode() != 200) {
            return resultJson;
        }

        return transportService.handleExc(parMap);
    }


}

