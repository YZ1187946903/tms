package cn.kj0901.tms.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tms_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private String id;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 登录密码
     */
    private String pwd;

    /**
     * 锁定状态(0：未锁定，1：已锁定)
     */
    private Integer lockState;

    /**
     * 用户名称 
     */
    private String nickName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 注册类型(0：门店，1：司机)
     */
    private Integer userType;

    /**
     * 删除状态(0：未删除，1：已删除)
     */
    private Integer delState;


}
