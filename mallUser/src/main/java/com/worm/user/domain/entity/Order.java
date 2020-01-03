package com.worm.user.domain.entity;

import java.util.Date;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "order")
public class Order {
    /**
     * 订单id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 商品id
     */
    @Column(name = "commodity_id")
    private Integer commodityId;

    /**
     * 商品数目
     */
    @Column(name = "commodity_num")
    private Integer commodityNum;

    /**
     * 订单总价格
     */
    private Float price;

    /**
     * 订单是否已支付 0：未支付；1：已支付
     */
    private Integer status;

    /**
     * 订单创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
}