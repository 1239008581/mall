package com.worm.user.domain.dto;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommodityDTO {
    /**
     * 商品id
     */
    private Integer id;

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
     * 商品所属分类id
     */
    private Integer classifyId;

    /**
     * 商品品牌id
     */
    private Integer brandId;

    /**
     * 商品价格
     */
    private Float price;
}