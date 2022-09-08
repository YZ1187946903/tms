package cn.kj0901.tms.manage.controller;


import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.entity.Goods;
import cn.kj0901.tms.base.entity.Warehouse;
import cn.kj0901.tms.base.util.ParamUtil;
import cn.kj0901.tms.manage.service.GoodsService;
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
 * 商品 前端控制器
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;
    /**
    * 方法介绍
    *   新增商品
    * @author Aedes
    * @date 2021/4/7 15:59
    * @return cn.kj0901.tms.base.config.ResultJson
    * @throws
    */
    @PostMapping("add")
    public ResultJson add(@RequestParam Map<String, Object> parMap) {

        String[] pars = {"warehouseId","goodsName","goodsNum", "goodsStock"};
        ResultJson resultJson = ParamUtil.checkParam(parMap, pars);
        if (resultJson.getCode() != 200) {
            return resultJson;
        }

        return goodsService.addInfo(parMap);

    }

    /**
     * 方法介绍
     * 查询商品列表
     *
     * @return java.util.List<cn.kj0901.tms.base.entity.Store>
     * @throws
     * @author Aedes
     * @date 2021/4/7 15:36
     */
    @PostMapping("list")
    public ResultJson list(@RequestParam Map<String, Object> parMap) {

        String[] pars = {"warehouseId"};
        ResultJson resultJson = ParamUtil.checkParam(parMap, pars);
        if (resultJson.getCode() != 200) {
            return resultJson;
        }

        Integer pageNum = 1;
        Integer pageSize = 10;
        ParamUtil.putPageInfo(parMap, pageNum, pageSize);


        List<Goods> storeList = goodsService.getGoodsList(parMap);
        PageInfo<Goods> pageInfo = new PageInfo<>(storeList);

        return ResultJson.ok(pageInfo);

    }

    /**
    * 方法介绍
    *   添加库存
    * @author Aedes
    * @date 2021/4/7 15:59
    * @return cn.kj0901.tms.base.config.ResultJson
    * @throws
    */
    @PostMapping("addStock")
    public ResultJson addStock(@RequestParam Map<String, Object> parMap) {

        String[] pars = {"id","goodsStock"};
        ResultJson resultJson = ParamUtil.checkParam(parMap, pars);
        if (resultJson.getCode() != 200) {
            return resultJson;
        }

        return goodsService.AddGoodsStock(parMap);

    }

}

