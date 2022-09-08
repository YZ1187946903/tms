package cn.kj0901.tms.base.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * KJ200901
 *
 * @Description : 提供司机和用户查询
 * @Author : Aedes
 * @Date: 2021/4/8 16:55
 */
@Data
public class DriverUserDTO  {

    /**
     * 主键id
     */
    private String id;

    /**
     * 司机名称
     */
    private String name;

    /**
     * 身份证号
     */
    private String cardNum;

    /**
     * 身份证url
     */
    private String cardUrl;

    /**
     * 身份证反面url
     */
    private String cardUrlContrary;

    /**
     * 驾驶证号
     */
    private String driverNum;

    /**
     * 驾驶证url
     */
    private String driverUrl;

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
     * 手机号
     */
    private String mobile;


}

