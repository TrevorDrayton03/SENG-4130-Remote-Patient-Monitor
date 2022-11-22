package com.company;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.util.Duration;

import java.util.Iterator;

public class JavaFXDashboard {
    public XYChart.Series<Number, Number> tempDataSeries;
    public XYChart.Series<Number, Number> brDataSeries;
    public XYChart.Series<Number, Number> hrDataSeries;
    // reusing the same series of data for multiple charts is NOT ok, need a different data series for a different chart (even if they contain the exact same information)
    public XYChart.Series<Number, Number> tempDataSeries2;
    public XYChart.Series<Number, Number> brDataSeries2;
    public XYChart.Series<Number, Number> hrDataSeries2;
    LineChart<Number, Number> chart; //reusing chart is OK for multiple graphs
    private NumberAxis xAxis;
    private NumberAxis xAxis2;
    private Timeline animation;
    private Timeline animation2;
    private double sequence = 0;
    private double sequence2 = 0;
    private final int MAX_DATA_POINTS = 50, MAX = 140, MIN = 5;
    Temperature temp = Temperature.getInstance();
    HeartRate hr = HeartRate.getInstance();
    BreathingRate br = BreathingRate.getInstance();
    private static JavaFXDashboard liveDataFeedLineChart = new JavaFXDashboard();
    TableView tableView;

    public static JavaFXDashboard getInstance() {
        return liveDataFeedLineChart;
    }

    public JavaFXDashboard() {
        animation = new Timeline();
        animation.getKeyFrames()
                .add(new KeyFrame(Duration.millis(1000),
                        (ActionEvent actionEvent) -> plotTime()));
        animation.setCycleCount(Animation.INDEFINITE);
        animation2 = new Timeline();
        animation2.getKeyFrames()
                .add(new KeyFrame(Duration.millis(1000),
                        (ActionEvent actionEvent) -> plotTime2()));
        animation2.setCycleCount(Animation.INDEFINITE);

        Group root = new Group();
        FlowPane pane = new FlowPane(createLiveTableDataFeed(), createLiveDataFeedLineChart());
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
        chart.setPrefSize(500, 300);

        return chart;
    }

    public Parent createLiveTableDataFeed() {
        tableView = new TableView();
        TableColumn<DataRow, Double> tempCol = new TableColumn<>("Temperature");
        TableColumn<DataRow, Double> hrCol = new TableColumn<>("Heart Rate");
        TableColumn<DataRow, Double> brCol = new TableColumn<>("Breathing Rate");
        tempCol.setCellValueFactory(new PropertyValueFactory<DataRow,Double>("tempData"));
        hrCol.setCellValueFactory(new PropertyValueFactory<DataRow,Double>("hrData"));
        brCol.setCellValueFactory(new PropertyValueFactory<DataRow,Double>("brData"));
        tableView.getColumns().add(tempCol);
        tableView.getColumns().add(hrCol);
        tableView.getColumns().add(brCol);

        return tableView;
    }

    private void plotTime() {
        tempDataSeries.getData().add(new XYChart.Data<Number, Number>(++sequence, getNextTempValue()));
        brDataSeries.getData().add(new XYChart.Data<Number, Number>(++sequence, getNextBRValue()));
        hrDataSeries.getData().add(new XYChart.Data<Number, Number>(++sequence, getNextHRValue()));

        if (sequence > MAX_DATA_POINTS - 1) {
            xAxis.setLowerBound(xAxis.getLowerBound() + 3);
            xAxis.setUpperBound(xAxis.getUpperBound() + 3);
        }
    }

    private void plotTime2() {
        tableView.getItems().add(new DataRow(getNextTempValue(), getNextHRValue(), getNextBRValue()));
/*        if (sequence2 > MAX_DATA_POINTS - 1) {
            xAxis2.setLowerBound(xAxis2.getLowerBound() + 3);
            xAxis2.setUpperBound(xAxis2.getUpperBound() + 3);
        }*/
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
        animation2.play();
    }

    public void stop() {
        animation.pause();
    }
}