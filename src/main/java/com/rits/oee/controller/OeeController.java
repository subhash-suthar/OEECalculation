package com.rits.oee.controller;

import com.rits.oee.model.OeeData;
import com.rits.oee.service.OeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/oee")
public class OeeController {

    private final OeeService oeeService;

    @Autowired
    public OeeController(OeeService oeeService) {
        this.oeeService = oeeService;
    }

    @PostMapping("/create")
    public ResponseEntity<OeeData> createOeeData(@RequestBody OeeData oeeData) {
        // Extract input values from the provided OeeData object
        double runtime = oeeData.getRuntime();
        double plannedProductionTime = oeeData.getPlannedProductionTime();
        double idealCycleTime = oeeData.getIdealCycleTime();
        int totalProducedCount = oeeData.getTotalProducedCount();
        int goodCount = oeeData.getGoodCount();

        // Calculate Availability, Performance, Quality, and OEE here
        double availability = calculateAvailability(runtime, plannedProductionTime);
        double performance = calculatePerformance(idealCycleTime, totalProducedCount, runtime);
        double quality = calculateQuality(goodCount, totalProducedCount);
        double oee = calculateOee(availability, performance, quality);

        oeeData.setAvailability(availability);
        oeeData.setPerformance(performance);
        oeeData.setQuality(quality);
        oeeData.setOee(oee);

        OeeData savedOeeData = oeeService.saveOeeData(oeeData);
        return new ResponseEntity<>(savedOeeData, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OeeData> getOeeDataById(@PathVariable String id) {
        OeeData oeeData = oeeService.getOeeDataById(id);
        if (oeeData != null) {
            return new ResponseEntity<>(oeeData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public List<OeeData> getAllOeeData() {
        return oeeService.getAllOeeData();
    }

    @GetMapping("/total-oee")
    public ResponseEntity<Double> getTotalOee(@RequestBody OeeData oeeData) {
        double totalOee = calculateTotalOee(oeeData);
        return new ResponseEntity<>(totalOee, HttpStatus.OK);
    }

    @GetMapping("/availability")
    public ResponseEntity<Double> getAvailability(@RequestBody OeeData oeeData) {
        double availability = calculateAvailability(oeeData.getRuntime(), oeeData.getPlannedProductionTime());
        return new ResponseEntity<>(availability, HttpStatus.OK);
    }

    @GetMapping("/performance")
    public ResponseEntity<Double> getPerformance(@RequestBody OeeData oeeData) {
        double performance = calculatePerformance(oeeData.getIdealCycleTime(), oeeData.getTotalProducedCount(), oeeData.getRuntime());
        return new ResponseEntity<>(performance, HttpStatus.OK);
    }

    @GetMapping("/quality")
    public ResponseEntity<Double> getQuality(@RequestBody OeeData oeeData) {
        double quality = calculateQuality(oeeData.getGoodCount(), oeeData.getTotalProducedCount());
        return new ResponseEntity<>(quality, HttpStatus.OK);
    }

    private double calculateAvailability(double runtime, double plannedProductionTime) {
        return runtime / plannedProductionTime;
    }

    private double calculatePerformance(double idealCycleTime, int totalProducedCount, double runtime) {
        return (idealCycleTime * totalProducedCount) / runtime;
    }

    private double calculateQuality(int goodCount, int totalProducedCount) {
        return (double) goodCount / totalProducedCount;
    }

    private double calculateOee(double availability, double performance, double quality) {
        return availability * performance * quality;
    }

    private double calculateTotalOee(OeeData oeeData) {
        double availability = calculateAvailability(oeeData.getRuntime(), oeeData.getPlannedProductionTime());
        double performance = calculatePerformance(oeeData.getIdealCycleTime(), oeeData.getTotalProducedCount(), oeeData.getRuntime());
        double quality = calculateQuality(oeeData.getGoodCount(), oeeData.getTotalProducedCount());
        return availability * performance * quality;
    }
}
