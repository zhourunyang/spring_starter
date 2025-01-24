package com.zry.controller;

import com.zry.pojo.Emp;
import com.zry.pojo.EmpQueryParam;
import com.zry.pojo.PageResult;
import com.zry.pojo.Result;
import com.zry.service.EmpService;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@Slf4j
@RestController
public class EmpController {

    //private static final Logger log = LoggerFactory.getLogger(EmpController.class);

    @Autowired
    private EmpService empService;

    /*@GetMapping("/emps")
    public Result page(String name, Integer gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("员工信息分页查询:page={},pageSize={}",page,pageSize);
        PageResult<Emp> pageResult = empService.page(name,gender,begin,end,page,pageSize);
        return Result.success(pageResult);
    }*/

    @GetMapping("/emps")
    public Result page(EmpQueryParam e){
        log.info("查询请求参数: {}",e);
        PageResult<Emp> pageResult = empService.page(e);
        return Result.success(pageResult);
    }

    @PostMapping("/emps")
    public Result save(@RequestBody Emp emp){
        log.info("新增员工:{}",emp);
        empService.save(emp);
        return Result.success();
    }

}
