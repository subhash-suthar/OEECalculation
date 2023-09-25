package com.rits.oee.service;

import com.rits.oee.model.OeeData;
import com.rits.oee.repository.OeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OeeServiceImpl implements OeeService {

    private final OeeRepository oeeRepository;

    public OeeServiceImpl(OeeRepository oeeRepository) {
        this.oeeRepository = oeeRepository;
    }

    @Override
    @Transactional
    public OeeData saveOeeData(OeeData oeeData) {
        // Perform any additional logic if needed
        return oeeRepository.save(oeeData);
    }

    @Override
    public OeeData getOeeDataById(String id) {
        return oeeRepository.findById(id).orElse(null);
    }

    @Override
    public List<OeeData> getAllOeeData() {
        return oeeRepository.findAll();
    }
}
