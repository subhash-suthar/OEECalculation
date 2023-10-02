package com.example.task.service;

import com.example.task.Data.OeeData;
import com.example.task.Repo.OeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public final class OeeServiceImpl implements OeeService {

    private final OeeRepository oeeRepository;

    public OeeServiceImpl(OeeRepository oeeRepository) {
        this.oeeRepository = oeeRepository;
    }

    @Override
    public OeeData saveOeeData(OeeData oeeData) {
        if (!isValidOeeData(oeeData)) {
            throw new IllegalArgumentException("Invalid OeeData object");
        }
        return oeeRepository.save(oeeData);
    }

    @Override
    public OeeData getOeeDataById(String id) {
        OeeData oeeData = oeeRepository.findById(id).orElse(null);
        if (oeeData == null) {
            throw new RuntimeException("OeeData not found with id: " + id);
        }
        return oeeData;
    }

    @Override
    public List<OeeData> calculateMetrics() {
        throw new UnsupportedOperationException("Method not implemented");
    }

    private boolean isValidOeeData(OeeData oeeData) {
        return true;
    }
}