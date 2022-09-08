package cn.kj0901.tms.base.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * KJ200901
 *
 * @Description :
 * @Author : Aedes
 * @Date: 2021/4/8 17:29
 */
@Data
public class TruckInfoDTO {
    /**
     * 主键id
     */
    private String id;

    /**
     * 司机id
     */
    private String driverId;

    /**
     * 车牌号
     */
    private String truckNum;

    /**
     * 车辆类别 A B C
     */
    private String truckType;

    /**
     * 燃油类型 0:汽油，1:天然气 2:电
     */
    private Integer fuelType;

    /**
     * 车辆识别代号
     */
    private String truckVin;

    /**
     * 道路运输证号
     */
    private String tranNum;

    /**
     * 车辆品牌型号
     */
    private String truckModel;

    /**
     * 发动机号码
     */
    private String engineNum;

    /**
     * 发证日期
     */
    private String certificateTime;

    /**
     * 报废日期
     */
    private LocalDateTime scrapTime;

    /**
     * 机动车行驶证正本图片
     */
    private String runImg;

    /**
     * 机动车行驶证副本图片
     */
    private String runImg2;

    /**
     * 道路运输证图片
     */
    private String transportImg;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 删除状态0：未删除，1：已删除
     */
    private Integer delState;

    /**
     * 司机名称
     */
    private String driverName;

    /**
     * 司机电话
     */
    private String driverMobile;

    /**
     * 车辆状态 0，空闲中  1，运输中
     */
    private Integer truckState;

}

