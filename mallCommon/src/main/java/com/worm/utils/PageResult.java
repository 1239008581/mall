package com.worm.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description: 封装分页后的数据格式
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PageResult {

    private int page;            // 当前页数
    private int total;           // 总页数
    private long records;        // 总记录数
    private List<?> rows;        // 每行显示的内容

}
