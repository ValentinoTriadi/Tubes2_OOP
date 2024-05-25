package oop.if2210_tb2_sc4.UI;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class ClockUI extends Pane {

    private static final double CLOCK_RADIUS = 100;
    private static final double CENTER_X = 150;
    private static final double CENTER_Y = 150;
    private static double COUNTDOWN_SECONDS;

    private final Arc remainingTimeArc;
    private double remainingSeconds = COUNTDOWN_SECONDS;
    private final Timeline timeline;

    public ClockUI(int timerSeconds) {
        COUNTDOWN_SECONDS = timerSeconds;

        Circle clockFace = new Circle(CENTER_X, CENTER_Y, CLOCK_RADIUS);
        clockFace.setFill(Color.WHITE);
        clockFace.setStroke(Color.BROWN);
        clockFace.setStrokeWidth(10);

        remainingTimeArc = new Arc(CENTER_X, CENTER_Y, CLOCK_RADIUS, CLOCK_RADIUS, 90, 360);
        remainingTimeArc.setType(ArcType.ROUND);
        remainingTimeArc.setFill(Color.RED);
        remainingTimeArc.setStroke(Color.RED);

        getChildren().addAll(clockFace, remainingTimeArc);
        timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> updateClock()));
        timeline.setCycleCount((int) (COUNTDOWN_SECONDS * 10));
    }

    public void startTimer() {
        remainingSeconds = COUNTDOWN_SECONDS;
        timeline.playFromStart();
    }

    private void updateClock() {
        if (remainingSeconds > 0) {
            double angle = 360 * (remainingSeconds / COUNTDOWN_SECONDS);
            remainingTimeArc.setLength(angle);
            remainingSeconds -= 0.1;
        } else {
            timeline.stop();
        }
    }

    public void setOnFinished(EventHandler<ActionEvent> onFinished) {
        timeline.setOnFinished(onFinished);
    }
}