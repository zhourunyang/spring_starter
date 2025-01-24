package com.zry.controller;

import com.zry.pojo.Dept;
import com.zry.pojo.Result;
import com.zry.service.DeptService;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class DeptController {

    //固定日志(Slf4j代替）
    //private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    /**
     * 查询所有部门
     * @return
     */
    @GetMapping("/depts")
    public Result list() {
        log.info("查询全部部门信息");
        List<Dept> DeptList = deptService.findAll();
        return Result.success(DeptList);
    }

    /**
     * 根据id删除部门
     * @param id
     * @return
     */
    @DeleteMapping("/depts")
    public Result delete(Integer id){
        log.info("根据id删除部门: {}",id);
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 新增部门
     * @param dept
     * @return
     */
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept){
        log.info("新增部门: {}",dept);
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 根据id查询部门 --路径参数
     * @return
     */
    @GetMapping("/depts/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询部门: {}",id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    /**
     * 更新部门
     * @param dept
     * @return
     */
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept){
        log.info("更新部门:{}",dept);
        deptService.update(dept);
        return Result.success();
    }
}
