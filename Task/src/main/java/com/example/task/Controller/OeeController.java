package com.example.task.Controller;

import com.example.task.Data.OeeData;
import com.example.task.service.OeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/production")
public class OeeController {

    private final OeeService oeeService;
    private final DecimalFormat decimalFormat;

    @Autowired
    public OeeController(OeeService oeeService) {
        this.oeeService = oeeService;
        this.decimalFormat = new DecimalFormat("0.0");
    }

    @PostMapping("/data")
    public ResponseEntity<OeeData> createOeeData(@RequestBody OeeData oeeData) {
        // Extract input values from the provided OeeData object
        String startTime = oeeData.getStartTime();
        String endTime = oeeData.getEndTime();
        double plannedProductionTime = oeeData.getPlannedProductionTime();
        int totalProducedCount = oeeData.getTotalProducedCount();
        int badCount = oeeData.getBadCount();

        // Calculate downtime (StopTime) in hours
        double downtime = calculateDowntime(startTime, endTime);

        // Calculate runtime using the updated downtime calculation
        double runtime = plannedProductionTime - downtime;

        double availability = calculateAvailability(runtime, plannedProductionTime)*100;
        double performance = calculatePerformance(oeeData.getCycleTime(), totalProducedCount, runtime)*100;
        double quality = calculateQuality(totalProducedCount, badCount)*100;

        double oee = calculateOee(availability/100, performance/100, quality/100)*100;

        // Set the calculated values back to the OeeData object
        oeeData.setDowntime(String.valueOf(Double.parseDouble(decimalFormat.format(downtime))));
        oeeData.setRuntime(String.valueOf(Double.parseDouble(decimalFormat.format(runtime))));
        oeeData.setAvailability(String.valueOf(Double.parseDouble(decimalFormat.format(availability))));
        oeeData.setPerformance(String.valueOf(Double.parseDouble(decimalFormat.format(performance))));
        oeeData.setQuality(String.valueOf(Double.parseDouble(decimalFormat.format(quality))));
        oeeData.setOee(String.valueOf(Double.parseDouble(decimalFormat.format(oee))));

        OeeData savedOeeData = oeeService.saveOeeData(oeeData);
        return new ResponseEntity<>(savedOeeData, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OeeData> getOeeMetricsById(@PathVariable String id) {
        OeeData oeeData = oeeService.getOeeDataById(id);
        if (oeeData != null) {
            return new ResponseEntity<>(oeeData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Add other endpoints for specific OEE metrics (availability, quality, performance) if needed

    private double calculateDowntime(String startTime, String endTime) {
        // Parse start and end times into LocalDateTime objects
        LocalDateTime start = LocalDateTime.parse(startTime, DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime end = LocalDateTime.parse(endTime, DateTimeFormatter.ISO_DATE_TIME);

        // Calculate the duration between start and end times
        Duration duration = Duration.between(start, end);

        // Calculate downtime in hours
        long downtimeMinutes = duration.toMinutes();
        return downtimeMinutes ; // Convert minutes to hours
    }

    private double calculateAvailability(double runtime, double plannedProductionTime) {
        return runtime / plannedProductionTime;
    }

    private double calculatePerformance(double cycleTime, int totalProducedCount, double runtime) {
        return (cycleTime * totalProducedCount) / runtime;
    }

    private double calculateQuality(int totalProducedCount, int badCount) {
        return (double) (totalProducedCount - badCount) / totalProducedCount;
    }

    private double calculateOee(double availability, double performance, double quality) {
        return availability * performance * quality;
    }
}
