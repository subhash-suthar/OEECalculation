package com.rits.oee.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "oeedatabase")
public class OeeData {

    @Id
    private String id;
    private double runtime;
    private double plannedProductionTime;
    private double idealCycleTime;
    private int totalProducedCount;
    private int goodCount;
    private double availability;
    private double performance;
    private double quality;
    private double oee;

    public OeeData(String id, double runtime, double plannedProductionTime, double idealCycleTime, int totalProducedCount, int goodCount, double availability, double performance, double quality, double oee) {
        this.id = id;
        this.runtime = runtime;
        this.plannedProductionTime = plannedProductionTime;
        this.idealCycleTime = idealCycleTime;
        this.totalProducedCount = totalProducedCount;
        this.goodCount = goodCount;
        this.availability = availability;
        this.performance = performance;
        this.quality = quality;
        this.oee = oee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getRuntime() {
        return runtime;
    }

    public void setRuntime(double runtime) {
        this.runtime = runtime;
    }

    public double getPlannedProductionTime() {
        return plannedProductionTime;
    }

    public void setPlannedProductionTime(double plannedProductionTime) {
        this.plannedProductionTime = plannedProductionTime;
    }

    public double getIdealCycleTime() {
        return idealCycleTime;
    }

    public void setIdealCycleTime(double idealCycleTime) {
        this.idealCycleTime = idealCycleTime;
    }

    public int getTotalProducedCount() {
        return totalProducedCount;
    }

    public void setTotalProducedCount(int totalProducedCount) {
        this.totalProducedCount = totalProducedCount;
    }

    public int getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(int goodCount) {
        this.goodCount = goodCount;
    }

    public double getAvailability() {
        return availability;
    }

    public void setAvailability(double availability) {
        this.availability = availability;
    }

    public double getPerformance() {
        return performance;
    }

    public void setPerformance(double performance) {
        this.performance = performance;
    }

    public double getQuality() {
        return quality;
    }

    public void setQuality(double quality) {
        this.quality = quality;
    }

    public double getOee() {
        return oee;
    }

    public void setOee(double oee) {
        this.oee = oee;
    }


}
