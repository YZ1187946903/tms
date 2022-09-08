package cn.kj0901.tms.base.entity.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * KJ200901
 *
 * @Description :
 * @Author : Aedes
 * @Date: 2021/4/13 15:51
 */
@Data
public class TransportInfoDTO {

    /**
     * 主键id
     */
    private String id;

    /**
     * 车辆id
     */
    private String truckId;

    /**
     * 司机id
     */
    private String driverId;

    /**
     * 状态(0：配货中，1：配送中，2：已送达,3：已完成,4：异常)
     */
    private Integer status;

    /**
     * 异常原因
     */
    private String excepInfo;

    /**
     * 创建生成时间
     */
    private LocalDateTime createTime;

    /**
     * 送达时间
     */
    private LocalDateTime sendTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 异常时间
     */
    private LocalDateTime excepTime;

    /**
     * 异常处理结束时间
     */
    private LocalDateTime excepEndTime;

    /**
     * 经度
     */
    private BigDecimal excepLon;

    /**
     * 维度
     */
    private BigDecimal excepLat;

    /**
     * 删除状态0：未删除，1：已删除
     */
    private Integer delState;

    /**
     * 门店名称
     */
    private String storeName;

    /**
     * 门店电话
     */
    private String storeMobile;


    private String truckNum;

    private String driverName;

    private String driverMobile;





}

