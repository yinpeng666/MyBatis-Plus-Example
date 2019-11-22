package com.fengwenyi.mybatis_plus_example.model;

import com.fengwenyi.mybatis_plus_example.controller.BaseDataLogHandler;
import lombok.Getter;

/**
 * 数据删除信息
 *
 * @author Tophua
 * @date 2019/8/5
 */
@Getter
public class DeleteInfo extends BaseDataLogHandler {

    /**
     * 删除对象
     */
    private Object deleteObj;

    public DeleteInfo(BasicInfo basicInfo, Object deleteObj) {
        super(basicInfo);
        this.deleteObj = deleteObj;
    }
}
