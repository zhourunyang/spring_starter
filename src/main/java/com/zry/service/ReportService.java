package com.zry.service;

import com.zry.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {

    JobOption getEmpJobData();

    List<Map> getEmpGenderData();
}
