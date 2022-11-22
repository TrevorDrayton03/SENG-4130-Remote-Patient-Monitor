package com.company;

import javafx.scene.chart.XYChart;

public class DataRow {
    private Double tempData = null;
    private Double hrData = null;
    private Double brData = null;
    private String timeData = null;
    private String dateData = null;

    public DataRow(Double tempDataSeries2, Double hrDataSeries2, Double brDataSeries2, String timeData, String dateData) {
        this.tempData = tempDataSeries2;
        this.brData = brDataSeries2;
        this.hrData = hrDataSeries2;
        this.timeData = timeData;
        this.dateData = dateData;
    }
    public Double getTempData() {
        return tempData;
    }

    public void setTempData(Double tempData) {
        this.tempData = tempData;
    }
    public Double getHrData() {
        return hrData;
    }

    public void setHrData(Double hrData) {
        this.hrData = hrData;
    }
    public Double getBrData() {
        return brData;
    }

    public void setBrData(Double brData) {
        this.brData = brData;
    }
    public String getTimeData() {
        return timeData;
    }

    public void setTimeData(String timeData) {
        this.timeData = timeData;
    }
    public String getDateData() {
        return dateData;
    }

    public void setDateData(String dateData) {
        this.dateData = dateData;
    }
}
