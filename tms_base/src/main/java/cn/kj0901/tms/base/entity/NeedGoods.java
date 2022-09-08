package cn.kj0901.tms.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 需求商品单
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tms_need_goods")
public class NeedGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 需求单id
     */
    private String needId;

    /**
     * 运输单id
     */
    private String transportId;

    /**
     * 商品id
     */
    private String goodsId;

    /**
     * 车辆id
     */
    private String truckId;

    /**
     * 司机id
     */
    private String driverId;

    /**
     * 商品数量
     */
    private Integer goodsNum;

    /**
     * 状态(0.待分配，1：待配货，2：配送中，3：已送达,4：已完成)
     */
    private Integer status;

    /**
     * 是否在临时中状态(0：不在，1：在)
     */
    private Integer tempStatus;

    /**
     * 创建生成时间
     */
    private LocalDateTime createTime;

    /**
     * 配货时间
     */
    private LocalDateTime loadTime;

    /**
     * 送达时间
     */
    private LocalDateTime sendTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 删除状态0：未删除，1：已删除
     */
    private Integer delState;


}
