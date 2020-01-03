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
@Table(name = "classify")
public class Classify {
    /**
     * 商品分类id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 商品分类名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 分类图片路径
     */
    private String image;

    /**
     * 父分类id，若此分类为分类顶点，则为-1
     */
    @Column(name = "father_id")
    private Integer fatherId;
}