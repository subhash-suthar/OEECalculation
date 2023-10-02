package com.example.task.service;


import com.example.task.Data.OeeData;

import java.util.List;


public interface OeeService {
    OeeData saveOeeData(OeeData oeeData);
    OeeData getOeeDataById(String id);

    List<OeeData> calculateMetrics();
}
