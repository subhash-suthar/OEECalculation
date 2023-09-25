package com.rits.oee.service;

import com.rits.oee.model.OeeData;
import java.util.List;

public interface OeeService {
    OeeData saveOeeData(OeeData oeeData);
    OeeData getOeeDataById(String id);
    List<OeeData> getAllOeeData();
}
