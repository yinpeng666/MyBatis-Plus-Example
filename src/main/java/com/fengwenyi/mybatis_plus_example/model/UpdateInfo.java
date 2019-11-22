package com.fengwenyi.mybatis_plus_example.model;

import com.fengwenyi.mybatis_plus_example.controller.BaseDataLogHandler;
import lombok.Getter;

import java.util.List;

/**
 * 数据更新信息
 *
 * @author Tophua
 * @date 2019/8/5
 */
@Getter
public class UpdateInfo extends BaseDataLogHandler {

    /**
     * 更新前对象
     */
    private Object oldObj;
    /**
     * 更新对象
     */
    private Object newObj;

    public UpdateInfo(BasicInfo basicInfo, Object oldObj, Object newObj) {
        super(basicInfo);
        this.oldObj = oldObj;
        this.newObj = newObj;
    }

    public List<CompareResult> getCompareResult() throws IllegalAccessException {
        return compareTowObject(this.oldObj, this.newObj);
    }
}
