package com.worm.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingDTO {
    /**
     * 购物id
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
     * 商品数量
     */
    private Integer commodityNum;

    /**
     * 总金额
     */
    private Float totalPrice;

}
