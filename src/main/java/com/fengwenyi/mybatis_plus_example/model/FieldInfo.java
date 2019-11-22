package com.fengwenyi.mybatis_plus_example.model;

import lombok.Data;

/**
 * 字段信息
 *
 * @author Tophua
 * @date 2019/8/5
 */
@Data
public class FieldInfo {

    /**
     * 字段名
     */
    private String fieldName;
    /**
     * java字段名
     */
    private String jFieldName;
    /**
     * 注释
     */
    private String comment;
}
