package com.fengwenyi.mybatis_plus_example.controller;

import com.fengwenyi.mybatis_plus_example.model.BasicInfo;
import com.fengwenyi.mybatis_plus_example.model.CompareResult;
import com.fengwenyi.mybatis_plus_example.model.FieldInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 数据日志基础信息及处理
 *
 * @author Tophua
 * @date 2019/8/5
 */
@Getter
@AllArgsConstructor
public abstract class BaseDataLogHandler {

    /**
     * 数据基础信息
     */
    private BasicInfo basicInfo;

    /**
     * 对比两个对象
     *
     * @param oldObj 旧对象
     * @param newObj 新对象
     * @return java.util.List<com.erp4cloud.rerp.common.data.log.CompareResult>
     * @author Tophua
     * @date 2019/8/5
     */
    protected List<CompareResult> compareTowObject(Object oldObj, Object newObj) throws IllegalAccessException {
        List<CompareResult> list = new ArrayList<>();
        //获取对象的class
        Class<?> clazz1 = oldObj.getClass();
        Class<?> clazz2 = newObj.getClass();
        //获取对象的属性列表
        Field[] field1 = clazz1.getDeclaredFields();
        Field[] field2 = clazz2.getDeclaredFields();
        //遍历属性列表field1
        for (int i = 0; i < field1.length; i++) {
            //遍历属性列表field2
            for (int j = 0; j < field2.length; j++) {
                //如果field1[i]属性名与field2[j]属性名内容相同
                if (field1[i].getName().equals(field2[j].getName())) {
                    field1[i].setAccessible(true);
                    field2[j].setAccessible(true);
                    if (field2[j].get(newObj) == null) {
                        continue;
                    }
                    //如果field1[i]属性值与field2[j]属性值内容不相同
                    if (!compareTwo(field1[i].get(oldObj), field2[j].get(newObj))) {
                        CompareResult r = new CompareResult();
                        r.setFieldName(field1[i].getName());
                        r.setOldValue(field1[i].get(oldObj));
                        r.setNewValue(field2[j].get(newObj));

                        // 匹配字段注释
                        Optional o = this.basicInfo.getFieldInfos().stream()
                                .filter(f -> r.getFieldName().equals(f.getJFieldName())).findFirst();
                        if (o.isPresent()) {
                            r.setFieldComment(((FieldInfo) o.get()).getComment());
                        }
                        list.add(r);
                    }
                    break;
                }
            }
        }
        return list;
    }

    /**
     * 对比两个数据是否内容相同
     *
     * @param object1,object2
     * @return boolean类型
     */
    private boolean compareTwo(Object object1, Object object2) {

        if (object1 == null && object2 == null) {
            return true;
        }
        if (object1 == null && object2 != null) {
            return false;
        }
        if (object1.equals(object2)) {
            return true;
        }
        return false;
    }

}
