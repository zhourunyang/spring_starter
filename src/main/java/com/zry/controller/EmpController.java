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
import java.util.Arrays;
import java.util.List;


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

    /**
     * 新增员工
     * @param emp
     * @return
     */
    @PostMapping("/emps")
    public Result save(@RequestBody Emp emp){
        log.info("新增员工:{}",emp);
        empService.save(emp);
        return Result.success();
    }

    /**
     * 批量删除员工
     * @param ids
     * @return
     */
    /**
     @DeleteMapping("/emps")
     public Result delete(Integer ids[]){
        log.info("批量删除部门: ids={} ", Arrays.asList(ids));
        return  Result.success();
    }
    */

    /**
     * 批量删除员工
     * @param ids
     * @return
     */
    @DeleteMapping("/emps")
    public Result delete(@RequestParam List<Integer> ids){
        log.info("批量删除部门: ids={} ", ids);
        empService.deleteByIds(ids);
        return Result.success();
    }

    /**
     * 查询回显
     * @param id
     * @return
     */
    @GetMapping("/emps/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据id查询员工的详细信息");
        Emp emp  = empService.getInfo(id);
        return Result.success(emp);
    }

    /**
     * 修改员工信息
     * @param emp
     * @return
     */
    @PutMapping("/emps")
    public Result update(@RequestBody Emp emp){
        log.info("修改员工信息, {}", emp);
        empService.update(emp);
        return Result.success();
    }
}
