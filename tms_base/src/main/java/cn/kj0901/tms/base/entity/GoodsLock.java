package cn.kj0901.tms.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品锁定
 * </p>
 *
 * @author kj0901
 * @since 2021-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tms_goods_lock")
public class GoodsLock implements Serializable {

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
     * 商品id
     */
    private String goodsId;

    /**
     * 锁定数量
     */
    private Integer lockNum;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
