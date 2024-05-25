package oop.if2210_tb2_sc4.UI;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import oop.if2210_tb2_sc4.MediaPlayer.AudioManager;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.Time;
import java.util.*;

import static java.lang.Math.min;

public class SeranganBeruang extends Thread {

    private final int col;
    private final int row;
    private volatile Time duration;
    private List<Point2D> path;
    private final LadangUI ladang;

    private volatile boolean isDone = false;
    private volatile boolean shouldStop = false;
    private SeranganBeruangUI seranganBeruangUI;
    private Pane bear = new Pane();
    private Label SBLabel;
    private Pane Clock;

    public SeranganBeruang(int col, int row, LadangUI ladang) {
        super();
        this.col = col; // Inisialisasi Kolom Serangan Beruang
        this.row = row; // Inisialisasi Baris Serangan Beruang
        this.ladang = ladang; // Inisialisasi DropZone Serangan Beruang
    }

    public void initializer(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SeranganBeruangUI.fxml"));
        try {
            bear = loader.load();
            seranganBeruangUI = loader.getController();
            seranganBeruangUI.setDropZone(ladang.getLadang());
            seranganBeruangUI.setLadang(ladang);
            bear.setVisible(false);
        } catch (IOException ignored) {}
    }

    public void setSBLabel(Label SBLabel) {
        this.SBLabel = SBLabel;
    }

    public void setClock(Pane Clock) {
        this.Clock = Clock;
    }

    public Pane getPane() {
        return bear;
    }

    public void serang() {
        duration = new Time(System.currentTimeMillis());
        int randomTime = (int) ((Math.random() * 0) + 15) * 1000;
        duration.setTime(randomTime);
        startTimer();

        // Melakukan Randomisasi serangan beruang yang akan terjadi
        if (randomSerangan()) {
            serangan_satu();
        } else {
            serangan_dua();
        }

        animateSBLabel();

        while (!isDone && !shouldStop) {
            try {
                //noinspection BusyWait
                Thread.sleep(10);
            } catch (InterruptedException ignored) {}
        }
        String soundFile = AudioManager.getInstance().getCardSoundMap().get("BearAttack");
        if (soundFile != null) {
            AudioManager.getInstance().playSFX(soundFile);
        }

        if (!shouldStop) {
            bear.setVisible(true);
            seranganBeruangUI.runAnimations(path);
        }
    }

    private void animateSBLabel() {
        if (SBLabel == null) return;
        SBLabel.setVisible(true);

        // Create a ScaleTransition
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), SBLabel);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(1.5);
        scaleTransition.setToY(1.5);
        scaleTransition.setCycleCount(4);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setOnFinished(event -> animateSBLabelLeave());

        // Start the transition
        scaleTransition.play();
    }

    private void animateSBLabelLeave(){
        // Create a ScaleTransition
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.1), SBLabel);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(0.0);
        scaleTransition.setToY(0.0);
        scaleTransition.setCycleCount(1);
        scaleTransition.setAutoReverse(false);

        // Add an event handler to set visibility to false after the transition
        scaleTransition.setOnFinished(event -> animateTimerCountdown());

        // Start the transition
        scaleTransition.play();
    }

    private void animateTimerCountdown(){
        SBLabel.setVisible(false);
        ClockUI clock = new ClockUI((int) (duration.getTime()/1000));

        // Add the clock to the pane
        Clock.getChildren().add(clock);
        Clock.setVisible(true);

        // Start the timer
        clock.startTimer();
        animateClockZoom();

        // Add an event handler to stop the thread after the timer finishes
        clock.setOnFinished(event -> animateClockLeave());

        // Animate the clock
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        final long[] previousTime = {System.nanoTime()};
        KeyFrame keyFrame = getKeyFrame(previousTime, clock, timeline);

        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    private static @NotNull KeyFrame getKeyFrame(long[] previousTime, ClockUI clock, Timeline timeline) {
        final boolean[] starter = {true};
        float speed = 100.0f;
        return new KeyFrame( Duration.millis((double) 1000 / 60), event -> {
            if (starter[0]) {
                previousTime[0] = System.nanoTime();
                starter[0] = false;
                return;
            }

            long currentTime = System.nanoTime();
            double deltaTime = (currentTime - previousTime[0]) / 1_000_000_000.0;
            previousTime[0] = currentTime;

            Point2D currentPosition = new Point2D(clock.getLayoutX(), clock.getLayoutY());

            if (currentPosition.getY() > -300) {
                clock.setLayoutY(currentPosition.getY() - speed * deltaTime);
            } else {
                timeline.stop();
            }
        });
    }

    private void animateClockZoom() {
        // Create a ScaleTransition
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.1), Clock);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(1.5);
        scaleTransition.setToY(1.5);
        scaleTransition.setCycleCount(5);
        scaleTransition.setAutoReverse(true);

        // Add an event handler to set the scale to 0.5 after the transition
        scaleTransition.setOnFinished(event -> animateClockSmall());

        // Start the transition
        scaleTransition.play();
    }

    private void animateClockSmall() {
        // Create a ScaleTransition
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), Clock);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(0.5);
        scaleTransition.setToY(0.5);
        scaleTransition.setCycleCount(1);
        scaleTransition.setAutoReverse(false);
        scaleTransition.setInterpolator(Interpolator.EASE_OUT);

        // Start the transition
        scaleTransition.play();
    }

    private void animateClockLeave(){
        // Create a ScaleTransition
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.1), Clock);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(0.0);
        scaleTransition.setToY(0.0);
        scaleTransition.setCycleCount(1);
        scaleTransition.setAutoReverse(false);

        // Add an event handler to set visibility to false after the transition
        scaleTransition.setOnFinished(event -> clearClock());

        // Start the transition
        scaleTransition.play();
    }

    private void clearClock(){
        Clock.setVisible(false);
        Clock.getChildren().clear();
    }


    private void startTimer(){
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.1), event -> {
            long timeLeft = duration.getTime() - 100;
            duration.setTime(timeLeft < 0 ? 0 : timeLeft);

            if (duration.getTime() <= 0) {
                timeline.stop();
                isDone = true;
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private boolean randomSerangan() {
        // Mengembalikan nilai random true atau false
        return Math.random() < 0.5;
    }

    private void serangan_satu() {
        // Melakukan randomisasi petak acuan serangan
        Point2D petakAcuan = getRandomAwalSerangan();

        double maxX = min(col - petakAcuan.getX(), 6);
        double maxY = min(row - petakAcuan.getY(), 6);

        // Melakukan Randomisasi area serangan
        Point2D areaSerangan = getRandomAreaSerangan(maxX, maxY);
        polaSeranganUlar(petakAcuan, areaSerangan);
    }

    private void polaSeranganUlar(Point2D petakAcuan, Point2D areaSerangan){
        for (int y = 0; y < areaSerangan.getY(); y++) {
            int start = y % 2 == 0 ? 0 : (int) areaSerangan.getX() - 1;
            int end = y % 2 == 0 ? (int) areaSerangan.getX() : -1;
            int step = y % 2 == 0 ? 1 : -1;
            for (int x = start; x != end; x += step) {
                if (shouldStop) return;
                path.add(new Point2D(petakAcuan.getX() + x, petakAcuan.getY() + y));
                ladang.setLadangOnRedColor((int) (petakAcuan.getY() + y - 1), (int) (petakAcuan.getX() + x - 1));
            }
        }
    }

    private Point2D getRandomAwalSerangan() {
        // Melakukan Randomisasi area serangan
        int x = (int) (Math.random() * col) + 1;
        int y = (int) (Math.random() * row) + 1;
        return new Point2D(x, y);
    }


    private Point2D getRandomAreaSerangan(double maxX, double maxY) {
        // Melakukan Randomisasi area serangan
        int y = (int) (Math.random() * maxY) + 1;
        int x = (int) (Math.random() * maxX) + 1;

        // Handler jika area serangan lebih dari 6 atau 0
        if (x * y > 6 || x * y == 0) {
            return getRandomAreaSerangan(maxX, maxY);
        }

        return new Point2D(x, y);
    }

    private void serangan_dua() {
        // Melakukan randomisasi petak acuan serangan
        Point2D petakAcuan = getRandomAwalSerangan();
        ladang.setLadangOnRedColor((int) petakAcuan.getY() - 1, (int) petakAcuan.getX() - 1);
        // Melakukan Randomisasi area serangan
        Set<Point2D> titikSerangan = getRandomTitikSerangan();
        for (Point2D point : titikSerangan) {
            if (shouldStop) return;
            ladang.setLadangOnRedColor((int) point.getY() - 1, (int) point.getX() - 1);
        }

        path.add(petakAcuan);
        // Melakukan serangan beruang dengan pola GBFS
        polaSeranganRandomGBFS(petakAcuan, titikSerangan);
    }

    private void polaSeranganRandomGBFS(Point2D start, Set<Point2D> track){
        Point2D end = getClosestPoint(start, track);
        if (end == null) return;
        moveFromStartToEnd(start, end);
        track.remove(end);
        if (!track.isEmpty()) {
            polaSeranganRandomGBFS(end, track);
        }
    }

    private Point2D getClosestPoint(Point2D start, Set<Point2D> track) {
        Point2D end = null;
        double minDistance = Double.MAX_VALUE;
        for (Point2D point : track) {
            double distance = start.distance(point);
            if (distance < minDistance) {
                minDistance = distance;
                end = point;
            }
        }
        return end;
    }

    private void moveFromStartToEnd(Point2D start, Point2D end) {
        while (!start.equals(end)) {
            if (shouldStop) return;
            start = getNextPoint(start, end);
            path.add(start);
        }
    }

    private Point2D getNextPoint(Point2D start, Point2D end) {
        if (start.getX() < end.getX()) {
            return new Point2D(start.getX() + 1, start.getY());
        } else if (start.getX() > end.getX()) {
            return new Point2D(start.getX() - 1, start.getY());
        } else if (start.getY() < end.getY()) {
            return new Point2D(start.getX(), start.getY() + 1);
        } else {
            return new Point2D(start.getX(), start.getY() - 1);
        }
    }


    private Set<Point2D> getRandomTitikSerangan(){
        // Set of Point
        Set<Point2D> titikSerangan = new HashSet<>();
        int jumlahSerangan = (int) (Math.random() * 5);
        // Melakukan Randomisasi area serangan
        while (titikSerangan.size() < jumlahSerangan) {
            int x = (int) (Math.random() * col) + 1;
            int y = (int) (Math.random() * row) + 1;
            titikSerangan.add(new Point2D(x, y));
        }

        return titikSerangan;
    }

    public void run() {
        path = new ArrayList<>();
        serang();
    }

    public void stopThread() {
        shouldStop = true;
    }
}