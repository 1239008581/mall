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
@Table(name = "brand_classify")
public class BrandClassify {
    /**
     * 分类品牌关系id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 分类id
     */
    @Column(name = "classify_id")
    private Integer classifyId;

    /**
     * 品牌id
     */
    @Column(name = "brand_id")
    private Integer brandId;
}