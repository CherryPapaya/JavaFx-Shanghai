package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShanghaiApplication extends Application {
    Data data = new Data();
    ArrayList<String> lines = this.data.readFile(data.getFile());
    Map<Integer, Integer> values = new HashMap<>();

    @Override
    public void start(Stage stage) {
        this.data.setValues(this.lines);
        this.values = this.data.getValues();

        NumberAxis xAxis = new NumberAxis(2006, 2018, 1);
        NumberAxis yAxis = new NumberAxis(0, 100, 10);

        xAxis.setLabel("Year");
        yAxis.setLabel("Rank");

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

        XYChart.Series rankData = new XYChart.Series();
        rankData.setName("University of Helsinki, Shanghai ranking");

        this.values.entrySet().stream().forEach(pair -> {
            rankData.getData().add(new XYChart.Data(pair.getKey(), pair.getValue()));
        });

        lineChart.getData().add(rankData);

        Scene scene = new Scene(lineChart, 640, 480);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(ShanghaiApplication.class);
    }

}
