package cn.kj0901.tms.manage.controller;


import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.entity.Store;
import cn.kj0901.tms.base.entity.Transport;
import cn.kj0901.tms.base.entity.dto.TranTempDTO;
import cn.kj0901.tms.base.util.ParamUtil;
import cn.kj0901.tms.manage.service.TransportTempService;
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
 * 运输临时单 前端控制器
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
@RestController
@RequestMapping("/transportTemp")
public class TransportTempController {

    @Autowired
    TransportTempService transportTempService;

    @PostMapping("add")
    public ResultJson add(@RequestParam Map<String, Object> parMap) {

        String[] pars = {"ids","adminId"};
        ResultJson resultJson = ParamUtil.checkParam(parMap, pars);
        if (resultJson.getCode() != 200) {
            return resultJson;
        }

        return transportTempService.add(parMap);

    }

    @PostMapping("delall")
    public ResultJson delAll(@RequestParam Map<String, Object> parMap) {

        String[] pars = {"adminId"};
        ResultJson resultJson = ParamUtil.checkParam(parMap, pars);
        if (resultJson.getCode() != 200) {
            return resultJson;
        }

        return transportTempService.delAll(parMap);

    }

    @PostMapping("list")
    public ResultJson list(@RequestParam Map<String, Object> parMap) {

        String[] pars = {"adminId"};
        ResultJson resultJson = ParamUtil.checkParam(parMap, pars);
        if (resultJson.getCode() != 200) {
            return resultJson;
        }
        //处理分页
        Integer pageNum = 1;
        Integer pageSize = 10;
        ParamUtil.putPageInfo(parMap, pageNum, pageSize);

        //查询并封装
        List<TranTempDTO> tranList = transportTempService.getList(parMap);
        PageInfo<TranTempDTO> pageInfo = new PageInfo<>(tranList);

        return ResultJson.ok(pageInfo);

    }



}

