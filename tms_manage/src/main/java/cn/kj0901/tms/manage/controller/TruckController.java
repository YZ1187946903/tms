package cn.kj0901.tms.manage.controller;


import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.entity.dto.DriverUserDTO;
import cn.kj0901.tms.base.entity.dto.TruckInfoDTO;
import cn.kj0901.tms.base.util.ParamUtil;
import cn.kj0901.tms.manage.service.TruckService;
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
 * 车辆 前端控制器
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
@RestController
@RequestMapping("/truck")
public class TruckController {

    @Autowired
    TruckService truckService;
    /**
     * 方法介绍
     *   司机信息插入
     * @author Aedes
     * @date 2021/4/8 17:11
     * @return cn.kj0901.tms.base.config.ResultJson
     * @throws
     */
    @PostMapping("setdriver")
    public ResultJson updateDriver(@RequestParam Map<String, Object> parMap) {
        //必填参数验证
        String[] pars = {"driverId", "id"};
        ResultJson resultJson = ParamUtil.checkParam(parMap, pars);
        if (resultJson.getCode() != 200) {
            return resultJson;
        }

        //信息插入并返回
        return truckService.updateDriverById(parMap);

    }

    /**
     * 方法介绍
     *   司机列表查询
     * @author Aedes
     * @date 2021/4/8 17:11
     * @return cn.kj0901.tms.base.config.ResultJson
     * @throws
     */
    @PostMapping("list")
    public ResultJson list(@RequestParam Map<String, Object> parMap) {
        //处理分页
        Integer pageNum = 1;
        Integer pageSize = 10;
        ParamUtil.putPageInfo(parMap, pageNum, pageSize);

        //查询并封装
        List<TruckInfoDTO> duList = truckService.getTruckList(parMap);
        PageInfo<TruckInfoDTO> pageInfo = new PageInfo<>(duList);

        return ResultJson.ok(pageInfo);

    }

}

