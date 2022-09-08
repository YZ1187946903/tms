package cn.kj0901.tms.manage.controller;


import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.entity.Store;
import cn.kj0901.tms.base.util.ParamUtil;
import cn.kj0901.tms.manage.service.StoreService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 门店 前端控制器
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
@RestController
@RequestMapping("/store")
public class StoreController {


    @Autowired
    StoreService storeService;

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
        //必填参数验证
        String[] pars = {"mobile", "address", "lat", "lon"};
        ResultJson resultJson = ParamUtil.checkParam(parMap, pars);
        if (resultJson.getCode() != 200) {
            return resultJson;
        }

        //信息插入并返回
        return storeService.addStoreAndUser(parMap);

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
        //处理分页
        Integer pageNum = 1;
        Integer pageSize = 10;
        ParamUtil.putPageInfo(parMap, pageNum, pageSize);


        //查询并封装
        List<Store> storeList = storeService.getStoreList(parMap);
        PageInfo<Store> pageInfo = new PageInfo<>(storeList);

        return ResultJson.ok(pageInfo);

    }

    /**
     * 方法介绍
     * 通过id修改门店信息
     *
     * @return cn.kj0901.tms.base.config.ResultJson
     * @throws
     * @author Aedes
     * @date 2021/4/6 15:39
     */
    @PostMapping("update")
    public ResultJson update(@RequestParam Map<String, Object> parMap) {
        //必填参数验证
        String[] pars = {"id"};
        ResultJson resultJson = ParamUtil.checkParam(parMap, pars);
        if (resultJson.getCode() != 200) {
            return resultJson;
        }

        //修改信息并返回
        return storeService.updateStoreInfoById(parMap);

    }

}

