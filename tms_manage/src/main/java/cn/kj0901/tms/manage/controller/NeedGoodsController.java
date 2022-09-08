package cn.kj0901.tms.manage.controller;


import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.entity.dto.NeedGoodsInfoDTO;
import cn.kj0901.tms.base.entity.dto.NeedInfoDTO;
import cn.kj0901.tms.base.util.ParamUtil;
import cn.kj0901.tms.manage.service.NeedGoodsService;
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
 * 需求商品单 前端控制器
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
@RestController
@RequestMapping("/needGoods")
public class NeedGoodsController {
    @Autowired
    NeedGoodsService needGoodsService;

    @PostMapping("listbyneed")
    public ResultJson add(@RequestParam Map<String, Object> parMap) {

        String[] pars = {"needId"};
        ResultJson resultJson = ParamUtil.checkParam(parMap, pars);
        if (resultJson.getCode() != 200) {
            return resultJson;
        }

        Integer pageNum = 1;
        Integer pageSize = 10;
        ParamUtil.putPageInfo(parMap, pageNum, pageSize);

        List<NeedGoodsInfoDTO> tranList = needGoodsService.getListByNeedId(parMap);
        PageInfo<NeedGoodsInfoDTO> pageInfo = new PageInfo<>(tranList);

        return ResultJson.ok(pageInfo);

    }

    @PostMapping("infolist")
    public ResultJson infolist(@RequestParam Map<String, Object> parMap) {

        Integer pageNum = 1;
        Integer pageSize = 10;
        ParamUtil.putPageInfo(parMap, pageNum, pageSize);

        List<NeedGoodsInfoDTO> tranList = needGoodsService.getInfoList(parMap);
        PageInfo<NeedGoodsInfoDTO> pageInfo = new PageInfo<>(tranList);

        return ResultJson.ok(pageInfo);

    }
    @PostMapping("tranlist")
    public ResultJson infolistByTranspot(@RequestParam Map<String, Object> parMap) {
        String[] pars = {"transportId"};
        ResultJson resultJson = ParamUtil.checkParam(parMap, pars);
        if (resultJson.getCode() != 200) {
            return resultJson;
        }
        Integer pageNum = 1;
        Integer pageSize = 10;
        ParamUtil.putPageInfo(parMap, pageNum, pageSize);

        List<NeedGoodsInfoDTO> tranList = needGoodsService.infolistByTranspot(parMap);
        PageInfo<NeedGoodsInfoDTO> pageInfo = new PageInfo<>(tranList);

        return ResultJson.ok(pageInfo);

    }

}

