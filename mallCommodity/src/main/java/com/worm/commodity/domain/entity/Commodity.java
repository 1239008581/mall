package com.worm.commodity.domain.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "commodity")
public class Commodity {
    /**
     * 商品id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 商品名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 商品描述
     */
    @Column(name = "`describe`")
    private String describe;

    /**
     * 商品销售量
     */
    @Column(name = "sale_num")
    private Integer saleNum;

    /**
     * 商品图品url
     */
    private String image;

    /**
     * 商品所属分类id
     */
    @Column(name = "classify_id")
    private Integer classifyId;

    /**
     * 商品品牌id
     */
    @Column(name = "brand_id")
    private Integer brandId;

    /**
     * 商品价格
     */
    private Float price;
}