package com.company;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Iterator;

public class AnimatedLineChart extends Application {
    private LineChart<Number, Number> chart;
    private XYChart.Series<Number, Number> tempDataSeries;
    private XYChart.Series<Number, Number> brDataSeries;
    private XYChart.Series<Number, Number> hrDataSeries;
    private NumberAxis xAxis;
    private Timeline animation;
    private double sequence = 0;
    private final int MAX_DATA_POINTS = 50, MAX = 140, MIN = 5;
    Temperature temp = Temperature.getInstance();
    HeartRate hr = HeartRate.getInstance();
    BreathingRate br = BreathingRate.getInstance();

    public AnimatedLineChart() {

        // create timeline to add new data every 60th of second
        animation = new Timeline();
        animation.getKeyFrames()
                .add(new KeyFrame(Duration.millis(1000),
                        (ActionEvent actionEvent) -> plotTime()));
        animation.setCycleCount(Animation.INDEFINITE);
    }

    public Parent createContent() {

        xAxis = new NumberAxis(0, MAX_DATA_POINTS, MAX_DATA_POINTS/10);

        final NumberAxis yAxis = new NumberAxis();
        chart = new LineChart<>(xAxis, yAxis);

        // setup chart
        chart.setAnimated(false);
        chart.setLegendVisible(false);
        chart.setTitle("Live Data Feed");
        xAxis.setLabel("Time");
        xAxis.setForceZeroInRange(false);

        yAxis.setLabel("");
        yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis, "", null));

        // add starting data
        tempDataSeries = new XYChart.Series<>();
        brDataSeries = new XYChart.Series<>();
        hrDataSeries = new XYChart.Series<>();
        tempDataSeries.setName("Temperature");
        brDataSeries.setName("Heart Rate");
        hrDataSeries.setName("Breathing Rate");

        chart.getData().add(tempDataSeries);
        chart.getData().add(brDataSeries);
        chart.getData().add(hrDataSeries);

        return chart;
    }

    private void plotTime() {
        tempDataSeries.getData().add(new XYChart.Data<Number, Number>(++sequence, getNextTempValue()));
        brDataSeries.getData().add(new XYChart.Data<Number, Number>(++sequence, getNextBRValue()));
        hrDataSeries.getData().add(new XYChart.Data<Number, Number>(++sequence, getNextHRValue()));

/*        if (sequence > MAX_DATA_POINTS) {
            tempDataSeries.getData().remove(0);
            brDataSeries.getData().remove(0);
            hrDataSeries.getData().remove(0);
        }*/

        if (sequence > MAX_DATA_POINTS - 1) {
            xAxis.setLowerBound(xAxis.getLowerBound() + 3);
            xAxis.setUpperBound(xAxis.getUpperBound() + 3);
        }
    }

    private double getNextTempValue() {
        Iterator tempData = temp.createIterator();
        return (double) tempData.next();
    }
    private double getNextBRValue() {
        Iterator brData = br.createIterator();
        return (double) brData.next();
    }
    private double getNextHRValue() {
        Iterator hrData = hr.createIterator();
        return (double) hrData.next();
    }
    public void play() {
        animation.play();
    }

    @Override
    public void stop() {
        animation.pause();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.setTitle("Live Data");
        primaryStage.show();
        play();
    }

}