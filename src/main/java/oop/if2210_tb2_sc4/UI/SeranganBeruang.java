package oop.if2210_tb2_sc4.UI;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import oop.if2210_tb2_sc4.MediaPlayer.AudioManager;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Math.min;

public class SeranganBeruang extends Thread {

    private final int col;
    private final int row;
    private Time duration;
    private List<Point2D> path;
    private DropZone[] dropZone;

    SeranganBeruangUI seranganBeruangUI;
    Pane bear = new Pane();

    public SeranganBeruang(int col, int row, DropZone[] dropZone) {
        super();
        this.col = col; // Inisialisasi Kolom Serangan Beruang
        this.row = row; // Inisialisasi Baris Serangan Beruang
        this.dropZone = dropZone; // Inisialisasi DropZone Serangan Beruang
    }

    public void initialize(){
        URL url = getClass().getResource("SeranganBeruangUI.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        try {
            bear = loader.load();
            seranganBeruangUI = loader.getController();
            seranganBeruangUI.setDropZone(dropZone);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Pane getPane(){
        return bear;
    }

    public Time getDuration() {
        return duration;
    }

    public void serang() {
        // Melakukan Randomisasi serangan beruang akan terjadi atau tidak
        if (!randomSerangan()) {
            return;
        }

        duration = new Time(System.currentTimeMillis());
        int randomTime = (int) ((Math.random() * 30) + 30) * 1000;
        duration.setTime(randomTime);

        System.out.println("Serangan Beruang selama " + duration.getSeconds() + " detik\n\n");

        // Melakukan Randomisasi serangan beruang yang akan terjadi
        if (randomSerangan()) {
            System.out.println("\n\nSerangan Beruang 1 ");
            serangan_satu();
        } else {
            System.out.println("\n\nSerangan Beruang 2 ");
            serangan_dua();
        }

        String soundFile = AudioManager.getInstance().getCardSoundMap().get("BearAttack");
        if (soundFile != null) {
            AudioManager.getInstance().playSFX(soundFile);
        }
        seranganBeruangUI.runAnimations(path);
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

        System.out.println("Serangan Beruang di (" + (int) petakAcuan.getX() + ", " + (int) petakAcuan.getY() + ") dengan area serangan " + areaSerangan.getX() + " x " + areaSerangan.getY());

        polaSeranganUlar(petakAcuan, areaSerangan);
    }

    private void polaSeranganUlar(Point2D petakAcuan, Point2D areaSerangan){
        for (int y = 0; y < areaSerangan.getY(); y++) {
            int start = y % 2 == 0 ? 0 : (int) areaSerangan.getX() - 1;
            int end = y % 2 == 0 ? (int) areaSerangan.getX() : -1;
            int step = y % 2 == 0 ? 1 : -1;
            for (int x = start; x != end; x += step) {
                path.add(new Point2D(petakAcuan.getX() + x, petakAcuan.getY() + y));
                System.out.printf("Serangan Beruang di (%d, %d)%n", (int) petakAcuan.getX() + x, (int) petakAcuan.getY() + y);
            }
        }
    }

    private Point2D getRandomAwalSerangan() {
        // Melakukan Randomisasi area serangan
        int x = (int) (Math.random() * col);
        int y = (int) (Math.random() * row);
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
        Point2D petakAcuan = new Point2D(0, 0);

        // Melakukan Randomisasi area serangan
        Set<Point2D> titikSerangan = getRandomTitikSerangan();

        polaSeranganRandomGBFS(petakAcuan, titikSerangan);
    }

    private void polaSeranganRandomGBFS(Point2D start, Set<Point2D> track){
        Point2D end = getClosestPoint(start, track);
        System.out.println("Serangan Beruang di (" + (int) end.getX() + ", " + (int) end.getY() + ")");
        moveFromStartToEnd(start, end);
        track.remove(end);
        if (!track.isEmpty()) {
            polaSeranganRandomGBFS(end, track);
        }
    }

    private Point2D getClosestPoint(Point2D start, Set<Point2D> track) {
        Point2D end = null;
        int minDistance = Integer.MAX_VALUE;
        for (Point2D point : track) {
            int distance = manhattanDistance(start, point);
            if (distance < minDistance) {
                minDistance = distance;
                end = point;
            }
        }
        return end;
    }

    private void moveFromStartToEnd(Point2D start, Point2D end) {
        while (!start.equals(end)) {
            start = getNextPoint(start, end);
            path.add(start);
            System.out.println("Serangan Beruang di (" + (int) start.getX() + ", " + (int) start.getY() + ")");
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

    private int manhattanDistance(Point2D start, Point2D end){
        return (int) (Math.abs(start.getX() - end.getX()) + Math.abs(start.getY() - end.getY()));
    }

    private Set<Point2D> getRandomTitikSerangan(){
        // Set of Point
        Set<Point2D> titikSerangan = new HashSet<>();

        // Melakukan Randomisasi area serangan
        while (titikSerangan.size() < 4) {
            int x = (int) (Math.random() * col);
            int y = (int) (Math.random() * row);
            titikSerangan.add(new Point2D(x, y));
        }

        return titikSerangan;
    }

    public void run() {
        path = new ArrayList<>();
        bear.setVisible(true);
        serang();
    }
}
