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
@Table(name = "footprint")
public class Footprint {
    /**
     * 足迹id
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
     * 访问时间
     */
    @Column(name = "create_time")
    private Date createTime;
}