package com.zry.service.Impl;

import com.zry.mapper.EmpLogMapper;
import com.zry.pojo.EmpLog;
import com.zry.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpLogServiceImpl implements EmpLogService {

    @Autowired
    private EmpLogMapper empLogMapper;

    /**
     * 插入日志
     * @param empLog
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)  //需要在一个新的事务中运行
    @Override
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }
}
