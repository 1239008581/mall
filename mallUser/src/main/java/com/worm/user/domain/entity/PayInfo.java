package com.worm.user.domain.entity;

import java.util.Date;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "pay_info")
public class PayInfo {
    /**
     * 支付信息主键id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 支付人id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 支付时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 支付金额
     */
    @Column(name = "pay_money")
    private Float payMoney;
}