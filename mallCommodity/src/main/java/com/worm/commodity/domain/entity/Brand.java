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
@Table(name = "brand")
public class Brand {
    /**
     * 品牌id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 品牌名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 品牌图片路径
     */
    private String image;
}