package cn.kj0901.tms.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 门店需求单
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tms_need")
public class Need implements Serializable {

    private static final long serialVersionUID = 1L;

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


}
