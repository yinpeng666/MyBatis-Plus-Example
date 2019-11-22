package com.fengwenyi.mybatis_plus_example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fengwenyi.mybatis_plus_example.model.CompareResult;
import com.fengwenyi.mybatis_plus_example.model.DeleteInfo;
import com.fengwenyi.mybatis_plus_example.model.InsertInfo;
import com.fengwenyi.mybatis_plus_example.model.UpdateInfo;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * describe
 *
 * @author Tophua
 * @date 2019/8/3
 */
@Service
@AllArgsConstructor
public class DataLogDeal implements DataLogHandler {

    ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public void insertHandler(InsertInfo insertInfo) {
        System.out.println("插入：" + objectMapper.writeValueAsString(insertInfo.getInsertObj()));
    }

    @SneakyThrows
    @Override
    public void updateHandler(UpdateInfo updateInfo) {
        List<CompareResult> cr = updateInfo.getCompareResult();
        StringBuilder sb = new StringBuilder();
        sb.append("更新\"");
        sb.append(updateInfo.getBasicInfo().getTbName());
        sb.append("\" 表 ");
        cr.forEach(r -> {
            String s = "把《" + r.getFieldComment() + "》从<" + r.getOldValue() + ">改成<" + r.getNewValue() + ">";
            sb.append(s);
        });
        System.out.println(sb.toString());
    }

    @SneakyThrows
    @Override
    public void deleteHandler(DeleteInfo deleteInfo) {
        System.out.println("删除:" + objectMapper.writeValueAsString(deleteInfo.getDeleteObj()));
    }
}
