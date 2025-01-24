package com.zry.service;

import com.zry.pojo.Emp;
import com.zry.pojo.EmpQueryParam;
import com.zry.pojo.PageResult;

import java.time.LocalDate;

public interface EmpService {

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    //PageResult<Emp> page(String name, Integer gender, LocalDate begin,LocalDate end,Integer page, Integer pageSize);

    /**
     * 封装请求的分页查询
     * @param e
     * @return
     */
    PageResult<Emp> page(EmpQueryParam e);

    /**
     * 新增员工
     * @param emp
     */
    void save(Emp emp);
}
