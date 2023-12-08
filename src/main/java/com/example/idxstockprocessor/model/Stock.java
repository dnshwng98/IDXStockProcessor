package com.example.idxstockprocessor.model;

public class Stock {
    private String code;
    private String name;
    private double relativeDailyPerformance = 0.0;
    private double dailyReturn = 0.0;

    public Stock() {}

    public Stock(String code, String name){
        this.code = code;
        this.name = name;
    };

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRelativeDailyPerformance() {
        return relativeDailyPerformance;
    }

    public void setRelativeDailyPerformance(double relativeDailyPerformance) {
        this.relativeDailyPerformance = relativeDailyPerformance;
    }

    public double getDailyReturn() {
        return dailyReturn;
    }

    public void setDailyReturn(double dailyReturn) {
        this.dailyReturn = dailyReturn;
    }
}
