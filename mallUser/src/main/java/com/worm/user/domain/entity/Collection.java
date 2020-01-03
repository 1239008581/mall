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
@Table(name = "collection")
public class Collection {
    /**
     * 收藏主键id
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
     * 收藏事物的id
     */
    @Column(name = "object_id")
    private Integer objectId;

    /**
     * 收藏事物的类型：commodity：商品；brand：品牌
     */
    private String type;

    /**
     * 收藏时间
     */
    @Column(name = "create_time")
    private Date createTime;
}