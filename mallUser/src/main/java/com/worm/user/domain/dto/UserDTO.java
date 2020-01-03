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
public class UserDTO {
    /**
     * 用户主键id
     */
    private Object id;

    /**
     * 用户微信openId
     */
    private String openId;

    /**
     * 用户微信昵称
     */
    private String wxNickname;

    /**
     * 用户头像地址
     */
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

    /**
     * 商品收藏数量
     */
    private Integer commodityCollectionNum;

    /**
     * 品牌收藏数量
     */
    private Integer brandCollectionNum;

    /**
     * 足迹数量
     */
    private Integer footprintNum;

    /**
     * 订单数量
     */
    private Integer orderNum;
}