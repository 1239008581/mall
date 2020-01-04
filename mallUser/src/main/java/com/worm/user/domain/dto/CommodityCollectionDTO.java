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
public class CommodityCollectionDTO {

    /**
     * 收藏主键id
     */
    private Integer id;

    /**
     * 商品id
     **/
    private Integer CommodityId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品描述
     */
    private String describe;

    /**
     * 商品图品url
     */
    private String image;

    /**
     * 商品价格
     */
    private Float price;

    /**
     * 收藏时间
     */
    private Date createTime;

}
