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
}
