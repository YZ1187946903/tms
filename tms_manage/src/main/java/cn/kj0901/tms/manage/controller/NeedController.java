package cn.kj0901.tms.manage.controller;


import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.entity.dto.NeedInfoDTO;
import cn.kj0901.tms.base.entity.dto.TransportInfoDTO;
import cn.kj0901.tms.base.util.ParamUtil;
import cn.kj0901.tms.manage.service.NeedService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    /**
     * 方法介绍
     *     查询列表
     * @author Aedes
     * @date 2021/4/13 15:48
     * @return cn.kj0901.tms.base.config.ResultJson
     * @throws
     */
    @PostMapping("list")
    public ResultJson list(@RequestParam Map<String, Object> parMap) {
        Integer pageNum = 1;
        Integer pageSize = 10;
        ParamUtil.putPageInfo(parMap, pageNum, pageSize);


        List<NeedInfoDTO> tranList = needService.getNeedInfoList(parMap);
        PageInfo<NeedInfoDTO> pageInfo = new PageInfo<>(tranList);

        return ResultJson.ok(pageInfo);

    }

    @PostMapping("info")
    public ResultJson info(@RequestParam Map<String, Object> parMap) {

        String[] pars = {"id"};
        ResultJson resultJson = ParamUtil.checkParam(parMap, pars);
        if (resultJson.getCode() != 200) {
            return resultJson;
        }

        NeedInfoDTO nd = needService.getNeedInfoById(parMap);
        return ResultJson.ok(nd);
    }

    @PostMapping("check")
    public ResultJson check(@RequestParam Map<String, Object> parMap){
        String[] pars = {"id","status"};
        ResultJson resultJson = ParamUtil.checkParam(parMap, pars);
        if (resultJson.getCode() != 200) {
            return resultJson;
        }

        return needService.checkNeed(parMap);
    }

}

