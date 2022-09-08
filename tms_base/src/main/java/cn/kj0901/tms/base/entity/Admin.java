package cn.kj0901.tms.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 管理员
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tms_admin")
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 员工姓名
     */
    private String name;

    /**
     * 登录账号
     */
    private String userName;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 是否删除 0：正常  1：删除
     */
    private Integer delState;


}
