package com.zry.mapper;

import com.zry.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpExprMapper {

    /**
     * 批量插入
     * @param exprList
     */
    void insertBatch(List<EmpExpr> exprList);

    /**
     * 根据员工id批量删除员工工作经历
     * @param empIds
     */
    void deleteByEmpIds(List<Integer> empIds);
}
