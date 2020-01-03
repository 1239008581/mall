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
@Table(name = "user")
public class User {
    /**
     * 用户主键id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 用户微信openId
     */
    @Column(name = "open_id")
    private String openId;

    /**
     * 用户微信昵称
     */
    @Column(name = "wx_nickname")
    private String wxNickname;

    /**
     * 用户头像地址
     */
    @Column(name = "avatar_url")
    private String avatarUrl;

    /**
     * 用户性别
     */
    private String sex;

    /**
     * 用户手机号
     */
    private String telephone;

    /**
     * 用户余额
     */
    private Float money;
}