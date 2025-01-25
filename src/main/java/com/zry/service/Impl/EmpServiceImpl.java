package com.zry.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zry.mapper.EmpExprMapper;
import com.zry.mapper.EmpMapper;
import com.zry.pojo.*;
import com.zry.service.EmpLogService;
import com.zry.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;
    /**
     * 原始分页查询
     * @param page
     * @param pageSize
     * @return
     */
    /*@Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        //调用mapper接口,查询总记录数
        Long total = empMapper.count();

        //调用mapper接口，查询结果
        Integer start = (page - 1) * pageSize;
        List<Emp> rows = empMapper.list(start, pageSize);

        //封装
        return new PageResult<>(total, rows);
    }*/

    /**
     * 条件分页查询
     * @param name
     * @param gender
     * @param begin
     * @param end
     * @param page
     * @param pageSize
     * @return
     */
    /*  @Override
        public PageResult<Emp> page(String name, Integer gender, LocalDate begin, LocalDate end, Integer page, Integer pageSize){
        //配置分页参数
        PageHelper.startPage(page,pageSize);

        //执行查询
        List<Emp> empList = empMapper.list(name,gender,begin,end);

        //封装结果并返回
        Page<Emp> p = (Page<Emp>)empList;
        return new PageResult<>(p.getTotal(),p.getResult());
    }*/

    /**
     * 分页查询
     * @param e
     * @return
     */
    @Override
    public PageResult<Emp> page(EmpQueryParam e){
        //配置分页参数
        PageHelper.startPage(e.getPage(),e.getPageSize());

        //执行查询
        List<Emp> empList = empMapper.list(e);

        //封装结果并返回
        Page<Emp> p = (Page<Emp>)empList;
        return new PageResult<>(p.getTotal(),p.getResult());
    }

    @Transactional(rollbackFor = Exception.class)   //spring事务管理
    @Override
    public void save(Emp emp) {

        try {
            //补全并保存员工基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            //保存员工工作经历
            List<EmpExpr> exprList = emp.getExprList();
            //如果员工工作经历不为空
            if(!CollectionUtils.isEmpty(exprList)){
                //遍历集合，对empId赋值
                exprList.forEach(empExpr ->{
                    empExpr.setEmpId(emp.getId());
                });
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //记录操作日志
            EmpLog empLog = new EmpLog(null,LocalDateTime.now(),"新增员工" + emp);
            empLogService.insertLog(empLog);
        }
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteByIds(List<Integer> ids) {
        //删除员工的基本信息
        empMapper.deleteByIds(ids);

        //删除员工的工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        //根据id修改员工的基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        //根据id修改员工的工作经历信息
        //先删除
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        //后添加
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr ->{
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }
    }

}
