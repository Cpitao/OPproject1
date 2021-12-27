package agh.ics.oop.guistats;

import agh.ics.oop.App;
import agh.ics.oop.stattrackers.AnimalStatsTracker;
import javafx.application.Platform;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

import java.util.NoSuchElementException;

public class AvgEnergyDisplay extends AbstractCountDisplay{

    private AnimalStatsTracker leftCountTracker;
    private AnimalStatsTracker rightCountTracker;

    public AvgEnergyDisplay(App app, double controlPanelWidth, AnimalStatsTracker leftCountTracker,
                            AnimalStatsTracker rightCountTracker) {
        super(app, controlPanelWidth, leftCountTracker, rightCountTracker);
        this.leftCountTracker = leftCountTracker;
        this.rightCountTracker = rightCountTracker;

    }

    @Override
    public void init() {
        xAxis = new NumberAxis();
        xAxis.setLabel("Era");
        yAxis = new NumberAxis();
        yAxis.setLabel("Animal average energy");

        lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Animal average energy over time");
        lineChart.setPrefWidth(controlPanelWidth);

        leftSeries.setName("Left map");
        rightSeries.setName("Right map");

        lineChart.getData().addAll(leftSeries, rightSeries);
        lineChart.setAnimated(false);
    }

    @Override
    public void updateGUI() {
        Platform.runLater(() -> {
            try { // happens when series is empty
                if (leftSeries.getData().size() > 2000)
                    leftSeries.getData().remove(0);
                leftSeries.getData().add(leftCountTracker.getAvgEnergyLastPoint());
            } catch (NoSuchElementException ignored) {
            }
            try {
                if (rightSeries.getData().size() > 2000)
                    rightSeries.getData().remove(0);
                rightSeries.getData().add(rightCountTracker.getAvgEnergyLastPoint());
            } catch (NoSuchElementException ignored) {
            }

        });
    }
}
