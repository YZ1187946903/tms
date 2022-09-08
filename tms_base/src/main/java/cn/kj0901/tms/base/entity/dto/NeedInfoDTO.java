package cn.kj0901.tms.base.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * KJ200901
 *
 * @Description :
 * @Author : Aedes
 * @Date: 2021/4/13 16:17
 */
@Data
public class NeedInfoDTO {


    /**
     * 主键id
     */
    private String id;

    /**
     * 门店id
     */
    private String storeId;

    /**
     * 状态(0：待审核，1：配送中，2：已完成,3：已驳回)
     */
    private Integer status;

    /**
     * 驳回原因
     */
    private String rejectInfo;

    /**
     * 创建生成时间
     */
    private LocalDateTime createTime;

    /**
     * 审核时间
     */
    private LocalDateTime checkTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

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

}

