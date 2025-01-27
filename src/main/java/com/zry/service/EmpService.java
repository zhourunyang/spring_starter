package com.zry.service;

import com.zry.pojo.Emp;
import com.zry.pojo.EmpQueryParam;
import com.zry.pojo.LoginInfo;
import com.zry.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

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

    /**
     * 批量删除员工
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据ID查询员工的详细信息(基本信息与工作经历信息）
     * @param id
     * @return
     */
    Emp getInfo(Integer id);

    /**
     * 修改员工信息
     * @param emp
     */
    void update(Emp emp);

    /**
     * 员工登录
     * @param emp
     * @return
     */
    LoginInfo login(Emp emp);
}
