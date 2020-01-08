package com.worm.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 购物车实体类
 * @author 彭通
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingCartDTO {

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 购物信息id列表
     */
    private List<Integer> shoppingIds;

    /**
     * 总价
     */
    private Integer totalPrice;

}
