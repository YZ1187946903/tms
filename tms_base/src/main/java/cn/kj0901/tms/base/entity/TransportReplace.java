package cn.kj0901.tms.base.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 运输单交接处理
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tms_transport_replace")
public class TransportReplace implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 运输单id
     */
    private String transportId;

    /**
     * 原车辆id
     */
    private String oldTruckId;

    /**
     * 原司机id
     */
    private String oldDriverId;

    /**
     * 新车辆id
     */
    private String truckId;

    /**
     * 新司机id
     */
    private String driverId;

    /**
     * 状态(0：处理中，1：已交接)
     */
    private Integer status;

    /**
     * 创建生成时间
     */
    private LocalDateTime createTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 异常时间
     */
    private LocalDateTime excepTime;

    /**
     * 经度
     */
    private BigDecimal lon;

    /**
     * 维度
     */
    private BigDecimal lat;

    /**
     * 异常处理结束时间
     */
    private LocalDateTime excepEndTime;

    /**
     * 删除状态0：未删除，1：已删除
     */
    private Integer delState;


}
