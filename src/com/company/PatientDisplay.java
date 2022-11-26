package com.company;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

//TODO: button to send data to doctor or export data to txt/csv file?

public class PatientDisplay {
    public XYChart.Series<Number, Number> tempDataSeries;
    public XYChart.Series<Number, Number> brDataSeries;
    public XYChart.Series<Number, Number> hrDataSeries;
    LineChart<Number, Number> chart;
    private NumberAxis xAxis;
    private Timeline animation;
    ListView<String> listView;
    private double sequence = 0;
    private Label timeLabel;
    private final int MAX_DATA_POINTS = 30;
    Temperature temp = Temperature.getInstance();
    HeartRate hr = HeartRate.getInstance();
    LocalDateTime time = LocalDateTime.now();
    LocalDateTime date = LocalDateTime.now();
    DateTimeFormatter format1 = DateTimeFormatter.ofPattern("HH:mm:ss");
    DateTimeFormatter format2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String formatTime = time.format(format1);
    String formatDate = time.format(format2);
    BreathingRate br = BreathingRate.getInstance();
    private static PatientDisplay patientDisplay = new PatientDisplay();
    public static PatientDisplay getInstance() {
        return patientDisplay;
    }
    public PatientDisplay() {
        animation = new Timeline();
        animation.getKeyFrames()
                .add(new KeyFrame(Duration.millis(1000),
                        (ActionEvent actionEvent) -> plotTime()));
        animation.setCycleCount(Animation.INDEFINITE);

        Group root = new Group();
        VBox vBox = new VBox(8);
        VBox vBox2 = new VBox(8);
        vBox.getChildren().addAll(createListViewWarnings());
        vBox2.getChildren().addAll(createTimeLabel(), createLiveDataFeedLineChart());
        vBox.setPrefWidth(400);
        vBox.setPrefHeight(400);
        FlowPane pane = new FlowPane(vBox, vBox2);

        pane.setOrientation(Orientation.VERTICAL);
        root.getChildren().add(pane);
        Scene scene = new Scene(root); // although this shows as not being used, it is required
    }
    public Parent createLiveDataFeedLineChart() {
        xAxis = new NumberAxis(0, MAX_DATA_POINTS, MAX_DATA_POINTS / 5);

        final NumberAxis yAxis = new NumberAxis();
        chart = new LineChart<>(xAxis, yAxis);

        // setup chart
        chart.setAnimated(false);
        chart.setLegendVisible(true);
        chart.setTitle("Live Data Feed");
        xAxis.setLabel(formatTime);
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
        chart.setPrefSize(500, 300);

        return chart;
    }
    public Parent createTimeLabel() {
        timeLabel = new Label();
        timeLabel.setText("               " + formatDate + " " + formatTime);
        timeLabel.setFont(new Font(30));
        timeLabel.setLayoutX(175);
        timeLabel.setLayoutY(125);
        return timeLabel;
    }
    public Parent createListViewWarnings() {
        listView = new ListView<String>();
        return listView;
    }
    private void plotTime() {
        time = LocalDateTime.now();
        date = LocalDateTime.now();
        formatTime = time.format(format1);
        formatDate = time.format(format2);
        tempDataSeries.getData().add(new XYChart.Data<>(++sequence, getNextTempValue()));
        brDataSeries.getData().add(new XYChart.Data<>(sequence, getNextBRValue()));
        hrDataSeries.getData().add(new XYChart.Data<>(sequence, getNextHRValue()));
        timeLabel.setText("               " + formatDate + " " + formatTime);
        xAxis.setLabel("Time(s)");
        double tempValue = getNextTempValue();
        double hrValue = getNextHRValue();
        double brValue = getNextBRValue();
        if (tempValue > 38.7 || tempValue < 34.3) {
            if (tempValue > 38.7) {
                listView.getItems().add(0, "Temperature high: " + tempValue + " Celsius at " + formatDate + " " + formatTime);
            } else if (tempValue < 34.3) {
                listView.getItems().add(0, "Temperature low: " + tempValue + " Celsius at " + formatDate + " " + formatTime);
            }
        } else if (hrValue < 45 || hrValue > 135) {
            if (hrValue > 135) {
                listView.getItems().add(0, "Heart rate high: " + hrValue + " per minute at " + formatDate + " " + formatTime);
            } else if (hrValue < 45) {
                listView.getItems().add(0, "Heart rate low: " + hrValue + " per minute at " + formatDate + " " + formatTime);
            }
        } else if (brValue < 8.5 || brValue > 19.5) {
            if (brValue > 19.5) {
                listView.getItems().add(0, "Breathing rate high: " + brValue + " per minute at " + formatDate + " " + formatTime);
            } else if (brValue < 8.5) {
                listView.getItems().add(0, "Breathing rate low: " + brValue + " per minute at " + formatDate + " " + formatTime);
            }
        }

        if (sequence > MAX_DATA_POINTS - 1) {
            xAxis.setLowerBound(xAxis.getLowerBound() + 1);
            xAxis.setUpperBound(xAxis.getUpperBound() + 1);
        }
    }
    private double getNextTempValue() {
        Iterator tempData = temp.createIterator();
        Double nextVal = (double) tempData.next();
        nextVal = nextVal * 100;
        nextVal = Double.valueOf(Math.round(nextVal));
        nextVal = nextVal / 100;
        return nextVal;
    }
    private double getNextBRValue() {
        Iterator brData = br.createIterator();
        Double nextVal = (double) brData.next();
        nextVal = nextVal * 100;
        nextVal = Double.valueOf(Math.round(nextVal));
        nextVal = nextVal / 100;
        return nextVal;
    }
    private double getNextHRValue() {
        Iterator hrData = hr.createIterator();
        Double nextVal = (double) hrData.next();
        nextVal = nextVal * 100;
        nextVal = Double.valueOf(Math.round(nextVal));
        nextVal = nextVal / 100;
        return nextVal;
    }
    public void play() {
        animation.play();
    }
    public void stop() {
        animation.pause();
    }
}