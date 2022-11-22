package com.company;

import javafx.scene.chart.XYChart;

public class DataRow {
    private Double tempData = null;
    private Double hrData = null;
    private Double brData = null;

    public DataRow(Double tempDataSeries2, Double hrDataSeries2, Double brDataSeries2) {
        this.tempData = tempDataSeries2;
        this.brData = brDataSeries2;
        this.hrData = hrDataSeries2;
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
}
