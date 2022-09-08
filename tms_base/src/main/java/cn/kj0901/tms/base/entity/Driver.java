package cn.kj0901.tms.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 司机
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tms_driver")
public class Driver implements Serializable {

    private static final long serialVersionUID = 1L;

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


}
