package com.company;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

public class ClinicianDisplay {
    public XYChart.Series<Number, Number> tempDataSeries;
    public XYChart.Series<Number, Number> brDataSeries;
    public XYChart.Series<Number, Number> hrDataSeries;
    LineChart<Number, Number> chart;
    private NumberAxis xAxis;
    private Timeline animation;
    ListView<String> listView;
    private double sequence = 0;
    private Label tempLabel;
    private Label hrLabel;
    private Label brLabel;
    private Label timeLabel;
    private final int MAX_DATA_POINTS = 30;
    Temperature temp = Temperature.getInstance();
    HeartRate hr = HeartRate.getInstance();
    LocalDateTime time = java.time.LocalDateTime.now();
    LocalDateTime date = java.time.LocalDateTime.now();
    DateTimeFormatter format1 = DateTimeFormatter.ofPattern("HH:mm:ss");
    DateTimeFormatter format2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String formatTime = time.format(format1);
    String formatDate = time.format(format2);
    BreathingRate br = BreathingRate.getInstance();
    private static ClinicianDisplay clinicianDisplay = new ClinicianDisplay();
    TableView tableView;
    public static ClinicianDisplay getInstance() {
        return clinicianDisplay;
    }
    public ClinicianDisplay() {
        animation = new Timeline();
        animation.getKeyFrames()
                .add(new KeyFrame(Duration.millis(1000),
                        (ActionEvent actionEvent) -> plotTime()));
        animation.setCycleCount(Animation.INDEFINITE);

        Group root = new Group();
        VBox vBox = new VBox(8);
        VBox vBox2 = new VBox(8);
        vBox.getChildren().addAll(createLiveAverageBreathRateFeed(), createLiveAverageHeartRateFeed(), createLiveAverageTempFeed(), createListViewWarnings());
        vBox2.getChildren().addAll(createTimeLabel(), createLiveDataFeedLineChart());
        vBox.setPrefWidth(400);
        vBox.setPrefHeight(400);
        FlowPane pane = new FlowPane(vBox, createLiveTableDataFeed(), vBox2);

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
    public Parent createLiveTableDataFeed() {
        tableView = new TableView();
        TableColumn<DataRow, Double> tempCol = new TableColumn<>("Temperature");
        TableColumn<DataRow, Double> hrCol = new TableColumn<>("Heart Rate");
        TableColumn<DataRow, Double> brCol = new TableColumn<>("Breathing Rate");
        TableColumn<DataRow, String> timeCol = new TableColumn<>("Time");
        TableColumn<DataRow, String> dateCol = new TableColumn<>("Date");
        tempCol.setCellValueFactory(new PropertyValueFactory<DataRow, Double>("tempData"));
        hrCol.setCellValueFactory(new PropertyValueFactory<DataRow, Double>("hrData"));
        brCol.setCellValueFactory(new PropertyValueFactory<DataRow, Double>("brData"));
        timeCol.setCellValueFactory(new PropertyValueFactory<DataRow, String>("timeData"));
        dateCol.setCellValueFactory(new PropertyValueFactory<DataRow, String>("dateData"));
        tableView.getColumns().add(tempCol);
        tableView.getColumns().add(hrCol);
        tableView.getColumns().add(brCol);
        tableView.getColumns().add(timeCol);
        tableView.getColumns().add(dateCol);

        return tableView;
    }
    public Parent createLiveAverageTempFeed() {
        tempLabel = new Label();
        tempLabel.setText("" + getNextTempValue());
        tempLabel.setFont(new Font(30));
        tempLabel.setLayoutX(175);
        tempLabel.setLayoutY(125);
        return tempLabel;
    }
    public Parent createTimeLabel() {
        timeLabel = new Label();
        timeLabel.setText("               " +formatDate + " " + formatTime);
        timeLabel.setFont(new Font(30));
        timeLabel.setLayoutX(175);
        timeLabel.setLayoutY(125);
        return timeLabel;
    }
    public Parent createLiveAverageBreathRateFeed() {
        brLabel = new Label();
        brLabel.setText("" + getNextBRValue());
        brLabel.setFont(new Font(30));
        brLabel.setLayoutX(175);
        brLabel.setLayoutY(125);
        return brLabel;
    }
    public Parent createListViewWarnings() {
        listView = new ListView<String>();
        return listView;
    }
    public Parent createLiveAverageHeartRateFeed() {
        hrLabel = new Label();
        hrLabel.setText("" + getNextHRValue());
        hrLabel.setFont(new Font(30));
        hrLabel.setLayoutX(175);
        hrLabel.setLayoutY(125);
        return hrLabel;
    }
    private void plotTime() {
        time = LocalDateTime.now();
        date = LocalDateTime.now();
        formatTime = time.format(format1);
        formatDate = time.format(format2);
        tempDataSeries.getData().add(new XYChart.Data<>(++sequence, getNextTempValue()));
        brDataSeries.getData().add(new XYChart.Data<>(sequence, getNextBRValue()));
        hrDataSeries.getData().add(new XYChart.Data<>(sequence, getNextHRValue()));
        tableView.getItems().add(new DataRow(getNextTempValue(), getNextHRValue(), getNextBRValue(), formatTime, formatDate));
        brLabel.setText("Average Breath Rate: " + getNextAvgBRValue());
        hrLabel.setText("Average Heart Rate: " + getNextAvgHRValue());
        tempLabel.setText("Average Temperature: " + getNextAvgTempValue());
        timeLabel.setText("               " +formatDate + " " + formatTime);
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
    private double getNextAvgHRValue() {
        Iterator hrData = hr.createIterator();
        double vals = 0.0;
        double count = 0.0;
        while(hrData.hasNext()){
            vals = vals + (double) hrData.next();
            count = count + 1.0;
        }
        vals = vals / count;
        vals = vals * 100;
        vals = Double.valueOf(Math.round(vals));
        vals = vals / 100;
        return vals;
    }
    private double getNextAvgTempValue() {
        Iterator tempData = temp.createIterator();
        double vals = 0.0;
        double count = 0.0;
        while(tempData.hasNext()){
            vals = vals + (double) tempData.next();
            count = count + 1.0;
        }
        vals = vals / count;
        vals = vals * 100;
        vals = Double.valueOf(Math.round(vals));
        vals = vals / 100;
        return vals;
    }
    private double getNextAvgBRValue() {
        Iterator brData = br.createIterator();
        double vals = 0.0;
        double count = 0.0;
        while(brData.hasNext()){
            vals = vals + (double) brData.next();
            count = count + 1.0;
        }
        vals = vals / count;
        vals = vals * 100;
        vals = Double.valueOf(Math.round(vals));
        vals = vals / 100;
        return vals;
    }
    public void play() {
        animation.play();
    }
    public void stop() {
        animation.pause();
    }
}