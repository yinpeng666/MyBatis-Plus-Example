package com.fengwenyi.mybatis_plus_example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fengwenyi.api_result.helper.ResultHelper;
import com.fengwenyi.api_result.model.ResultModel;
import com.fengwenyi.javalib.util.StringUtil;
import com.fengwenyi.mybatis_plus_example.business.AppBusiness;
import com.fengwenyi.mybatis_plus_example.enums.GenderEnum;
import com.fengwenyi.mybatis_plus_example.model.City;
import com.fengwenyi.mybatis_plus_example.model.Idcard;
import com.fengwenyi.mybatis_plus_example.model.Student;
import com.fengwenyi.mybatis_plus_example.service.MPCityService;
import com.fengwenyi.mybatis_plus_example.service.MPStudentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试
 *
 * @author Wenyi Feng
 * @since 2018-09-01
 */
@RestController
@RequestMapping(value = "/app", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "App 测试示例")
public class AppController {

    @Autowired
    private MPCityService mpCityService;

    @Autowired
    private AppBusiness appBusiness;

    @Autowired
    private MPStudentService mpStudentService;

    // 查询所有城市
    @GetMapping("/queryCityAll")
    public ResultModel queryCityAll() {
        List<City> cities = mpCityService.queryCityAll();
        return ResultHelper.success("Success", cities);
    }

    @PatchMapping("/updataCity/{id}")
    public ResultModel updataCity(@PathVariable("id")Long id){
        List<City> cities = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            City city = new City();
            city.setId(Long.valueOf(i));
            city.setAreaName(i+"啊");
            cities.add(city);
        }
        mpCityService.updateBatchById(cities);
        return ResultHelper.success("修改成功");
    }

    // 添加城市
    @PostMapping("/addCity")
    public ResultModel addCity(String name) {
        if (StringUtil.isEmpty(name))
            return ResultHelper.error("名称不能为空");
        boolean rs = mpCityService.addCity(new City().setAreaName(name));
        if (rs)
            return ResultHelper.success("Success", null);
        return ResultHelper.error("添加失败");
    }

    // 添加学生
    @PostMapping("/addStudent")
    public ResultModel addStudent(String name, Integer age, String gender, String info, String idCardCode, String cityName) {

        // 检验参数
        if (StringUtil.isEmpty(name)
                || age == null
                || StringUtil.isEmpty(gender)
                || StringUtil.isEmpty(info)
                || StringUtil.isEmpty(idCardCode)
                || StringUtil.isEmpty(cityName))
            return ResultHelper.error("参数不合法");

        // 获取GenderEnum
        GenderEnum genderEnum = GenderEnum.getEnumByDesc(gender);

        // student
        Student student = new Student()
                .setName(name)
                .setAge(age)
                .setGender(genderEnum)
                .setInfo(info);

        // city
        City city = new City().setAreaName(cityName);

        // idCard
        Idcard idcard = new Idcard().setCode(idCardCode);

        // service
        boolean rs = appBusiness.addStudent(student, city, idcard);
        if (rs)
            return ResultHelper.success("Success", null);
        return ResultHelper.error("添加失败");
    }

    // 分页查询学生
    @GetMapping("/queryStudentByPage/{currentPage}")
    public ResultModel queryStudentByPage(@PathVariable("currentPage") Long currentPage) {
        if (currentPage == null)
            return ResultHelper.error("当前页不能为空");
        IPage<Student> studentIPage = mpStudentService.queryStudentByPage(currentPage);
        return ResultHelper.success("Success", studentIPage);
    }

    @GetMapping("/testFenCi")
    public ResultModel testFenCi(String text) throws IOException {
//        text="中国太平成立九十周年了！";
        List<String> list=this.cut(text);
        System.out.println(list);
        return ResultHelper.success("Success",list);
    }

    public List<String> cut(String msg) throws IOException {
        StringReader sr=new StringReader(msg);
        IKSegmenter ik=new IKSegmenter(sr, true);
        Lexeme lex=null;
        List<String> list=new ArrayList<>();
        while((lex=ik.next())!=null){
            list.add(lex.getLexemeText());
        }
        return list;
    }
}
