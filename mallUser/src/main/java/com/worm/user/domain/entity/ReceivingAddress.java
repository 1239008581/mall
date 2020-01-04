package com.worm.user.domain.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "receiving_address")
public class ReceivingAddress {
    /**
     * 收货地址主键id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_Id")
    private Integer userId;

    /**
     * 收货省
     */
    private String province;

    /**
     * 收货城市
     */
    private String city;

    /**
     * 收货县/区
     */
    private String area;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 收货人姓名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 收货人电话
     */
    @Column(name = "user_telephone")
    private String userTelephone;
}