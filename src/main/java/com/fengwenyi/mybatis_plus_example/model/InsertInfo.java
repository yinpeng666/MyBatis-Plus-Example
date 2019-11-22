package com.fengwenyi.mybatis_plus_example.model;

import com.fengwenyi.mybatis_plus_example.controller.BaseDataLogHandler;
import lombok.Getter;

/**
 * 数据插入信息
 *
 * @author Tophua
 * @date 2019/8/5
 */
@Getter
public class InsertInfo extends BaseDataLogHandler {

    /**
     * 插入对象
     */
    private Object insertObj;

    public InsertInfo(BasicInfo basicInfo, Object insertObj) {
        super(basicInfo);
        this.insertObj = insertObj;
    }

}
