package com.worm.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {

    /**
     * 订单id
     */
    private Integer id;

    /**
     * 商品id
     */
    private Integer commodityId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品描述
     */
    private String describe;

    /**
     * 商品销售量
     */
    private Integer saleNum;

    /**
     * 商品图品url
     */
    private String image;

    /**
     * 商品数目
     */
    private Integer commodityNum;

    /**
     * 订单创建时间
     */
    private Date createTime;

    /**
     * 总价
     */
    private Float totalPrice;

}
