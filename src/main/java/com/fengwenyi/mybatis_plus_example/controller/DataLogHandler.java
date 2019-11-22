package com.fengwenyi.mybatis_plus_example.controller;

import com.fengwenyi.mybatis_plus_example.model.DeleteInfo;
import com.fengwenyi.mybatis_plus_example.model.InsertInfo;
import com.fengwenyi.mybatis_plus_example.model.UpdateInfo;

/**
 * 数据日志处理
 *
 * @author Tophua
 * @date 2019/8/2
 */
public interface DataLogHandler {

    /**
     * 插入处理
     *
     * @param insertInfo 插入数据信息
     * @return void
     * @author Tophua
     * @date 2019/8/2
     */
    void insertHandler(InsertInfo insertInfo);

    /**
     * 更新处理
     *
     * @param updateInfo 更新数据信息
     * @return void
     * @author Tophua
     * @date 2019/8/2
     */
    void updateHandler(UpdateInfo updateInfo);

    /**
     * 删除处理
     *
     * @param deleteInfo 删除数据信息
     * @return void
     * @author Tophua
     * @date 2019/8/3
     */
    void deleteHandler(DeleteInfo deleteInfo);
}
