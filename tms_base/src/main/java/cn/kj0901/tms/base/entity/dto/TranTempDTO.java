package cn.kj0901.tms.base.entity.dto;

import lombok.Data;

/**
 * KJ200901
 *
 * @Description :
 * @Author : Aedes
 * @Date: 2021/4/14 15:38
 */
@Data
public class TranTempDTO {
    /**
     * 主键id
     */
    private String id;

    /**
     * 管理员id
     */
    private String adminId;

    /**
     * 商品单id
     */
    private String needGoodsId;

    private String goodsName;
    /**
    *商品数量
     */
    private String goodsNum;

    private String warehouseName;

    private String storeName;



}

