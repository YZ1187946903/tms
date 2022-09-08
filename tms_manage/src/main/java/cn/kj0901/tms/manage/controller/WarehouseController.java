package cn.kj0901.tms.manage.controller;


import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.entity.Store;
import cn.kj0901.tms.base.entity.Warehouse;
import cn.kj0901.tms.base.util.ParamUtil;
import cn.kj0901.tms.manage.service.StoreService;
import cn.kj0901.tms.manage.service.WarehouseService;
import com.github.pagehelper.PageHelper;
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
 * 仓库 前端控制器
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    /**
     * 方法介绍
     * 完成用户和门店的添加操作
     *
     * @return cn.kj0901.tms.base.config.ResultJson
     * @throws
     * @author Aedes
     * @date 2021/4/6 15:07
     */
    @PostMapping("add")
    public ResultJson add(@RequestParam Map<String, Object> parMap) {

        String[] pars = {"name","wareMobile","address", "lat", "lon"};
        ResultJson resultJson = ParamUtil.checkParam(parMap, pars);
        if (resultJson.getCode() != 200) {
            return resultJson;
        }

        return warehouseService.addInfo(parMap);

    }

    /**
     * 方法介绍
     * 查询门店列表
     *
     * @return java.util.List<cn.kj0901.tms.base.entity.Store>
     * @throws
     * @author Aedes
     * @date 2021/4/6 15:36
     */
    @PostMapping("list")
    public ResultJson list(@RequestParam Map<String, Object> parMap) {

        Integer pageNum = 1;
        Integer pageSize = 10;
        ParamUtil.putPageInfo(parMap, pageNum, pageSize);

        List<Warehouse> storeList = warehouseService.getWarehouseList(parMap);
        PageInfo<Warehouse> pageInfo = new PageInfo<>(storeList);

        return ResultJson.ok(pageInfo);

    }

}

