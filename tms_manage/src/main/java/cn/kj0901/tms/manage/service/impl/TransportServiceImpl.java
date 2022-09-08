package cn.kj0901.tms.manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.kj0901.tms.base.config.ResultJson;
import cn.kj0901.tms.base.dao.*;
import cn.kj0901.tms.base.entity.*;
import cn.kj0901.tms.base.entity.dto.TransportInfoDTO;
import cn.kj0901.tms.manage.service.TransportService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * 运输单 服务实现类
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
@Service
public class TransportServiceImpl extends ServiceImpl<TransportDao, Transport> implements TransportService {
	@Resource
	TransportTempDao tempDao;

	@Resource
	NeedGoodsDao needGoodsDao;

	@Resource
	TruckDao truckDao;

	@Resource
	TransportDao transportDao;

	@Resource
	TransportReplaceDao transportReplaceDao;

	@Override
	@Transactional
	public ResultJson add(Map<String, Object> parMap) {
		//处理查询条件
		QueryWrapper<TransportTemp> qw = new QueryWrapper<>();
		qw.eq("admin_id", parMap.get("adminId"));

		//查询需要修改的列表
		List<TransportTemp> tempList = tempDao.selectList(qw);


		//查询车辆信息
		Truck truck = truckDao.selectById(parMap.get("truckId").toString());
		if (truck.getDriverId() == null) {
			return ResultJson.err(203, "车辆信息不完成，请补全");
		}

		//创建运输单
		Transport transport = new Transport();
		transport.setId(IdUtil.simpleUUID());
		transport.setTruckId(truck.getId());
		transport.setDriverId(truck.getDriverId());
		transport.setCreateTime(LocalDateTime.now());
		if (transportDao.insert(transport) == 0) {
			return ResultJson.err(203, "创建运输单失败");
		}

		//批量修改商品单信息
		List<String> idList = new ArrayList<>();
		//lambda表达式-不能操作栈中的数据（局部变量），内部类-方法
		//AtomicInteger a = new AtomicInteger(2);


		tempList.forEach(temp -> {
			// a.set(temp.getId().hashCode());
			idList.add(temp.getNeedGoodsId());
		});
		if (needGoodsDao.updateTransportInfoByList(idList, transport) != idList.size()) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return ResultJson.err(203, "商品需求处理失败");
		}

		//删除临时信息
		if (tempDao.delete(qw) != tempList.size()) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return ResultJson.err(203, "商品信息删除失败");
		}
		return ResultJson.ok();

	}

	@Override
	public List<TransportInfoDTO> getInfoList(Map<String, Object> parMap) {
		return transportDao.getInfoDTOList(parMap);
	}

	@Override
	public ResultJson getInfo(Map<String, Object> parMap) {
		List<TransportInfoDTO> transportInfoDTOS = transportDao.getInfoDTOList(parMap);
		if (transportInfoDTOS.get(0) == null) {
			return ResultJson.err(203, "数据不存在");
		}

		transportDao.getInfoDTOList(parMap);

		return ResultJson.ok(transportInfoDTOS.get(0));
	}

	@Override
	@Transactional
	public ResultJson handleExc(Map<String, Object> parMap) {
		Truck truck = truckDao.selectById(parMap.get("truckId").toString());
		if (truck == null || truck.getDriverId() == null) {
			return ResultJson.err(203, "车辆数据不完成，请确认！");
		}
		Transport transport = transportDao.selectById(parMap.get("id").toString());
		if (transport == null) {
			return ResultJson.err(203, "运输单信息不存在");
		}
		TransportReplace transportReplace = new TransportReplace();
		transportReplace.setId(IdUtil.simpleUUID());
		transportReplace.setTransportId(transport.getId());
		transportReplace.setOldDriverId(transport.getDriverId());
		transportReplace.setOldTruckId(transport.getTruckId());
		transportReplace.setTruckId(truck.getId());
		transportReplace.setDriverId(truck.getDriverId());
		transportReplace.setCreateTime(LocalDateTime.now());
		transport.setStatus(1);
		if (transportDao.updateById(transport) != 1) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return ResultJson.err(203, "状态修改失败！");
		}

		if (transportReplaceDao.insert(transportReplace) == 0) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return ResultJson.err(203, "交接单处理失败！");
		}

		return ResultJson.ok();
	}
}
