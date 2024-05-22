package oop.if2210_tb2_sc4.UI;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import oop.if2210_tb2_sc4.card.AnimalCard;
import oop.if2210_tb2_sc4.card.AnimalType;
import oop.if2210_tb2_sc4.card.ProductCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static oop.if2210_tb2_sc4.GameData.getCard;

public class SeranganBeruangUI {

    @FXML
    public ImageView BearImage;
    public ImageView ShockWaveImage;
    public Pane root;
    private AnchorPane RootPane;

    private LadangUI ladang;

    private int currentImageIndex = 0;
    private int currentShockWaveIndex = 0;

    private final Image[] imageBearUp = new Image[3];
    private final Image[] imageBearDown = new Image[3];
    private final Image[] imageBearLeft = new Image[3];
    private final Image[] imageBearRight = new Image[3];
    private final Image[] imageShockWave = new Image[8];

    private DropZone[] dropZone;
    private boolean startAnimation = false;
    private final List<Animation> animations = new ArrayList<>();
    private Animation currentAnimation;

    private final float range = 10.0f;
    private final Object lock = new Object();

    public void setStartAnimation(boolean value) {
        synchronized (lock) {
            startAnimation = value;
        }
    }

    public boolean isStartAnimation() {
        synchronized (lock) {
            return startAnimation;
        }
    }


    public SeranganBeruangUI() {
        super();
    }

    public void setLadang(LadangUI ladang) {
        this.ladang = ladang;
    }

    public void setDropZone(DropZone[] dropZone) {
        this.dropZone = dropZone;
    }

    public void initialize() {

        for (int i = 1; i <= imageBearUp.length; i++) {
            String path = "assets/bear/bear_up_" + i + ".png";
            imageBearUp[i-1] = new Image(Objects.requireNonNull(CardUI.class.getResource(path)).toExternalForm());
        }

        for (int i = 1; i <= imageBearDown.length; i++) {
            String path = "assets/bear/bear_down_" + i + ".png";
            imageBearDown[i - 1] = new Image(Objects.requireNonNull(CardUI.class.getResource(path)).toExternalForm());
        }

        for (int i = 1; i <= imageBearLeft.length; i++) {
            String path = "assets/bear/bear_left_" + i + ".png";
            imageBearLeft[i-1] = new Image(Objects.requireNonNull(CardUI.class.getResource(path)).toExternalForm());
        }

        for (int i = 1; i <= imageBearRight.length; i++) {
            String path = "assets/bear/bear_right_" + i + ".png";
            imageBearRight[i-1] = new Image(Objects.requireNonNull(CardUI.class.getResource(path)).toExternalForm());
        }

        for (int i = 1; i <= imageShockWave.length; i++) {
            String path = "assets/bear/shockwave_" + i + ".png";
            imageShockWave[i-1] = new Image(Objects.requireNonNull(CardUI.class.getResource(path)).toExternalForm());
        }

        BearImage.setImage(imageBearLeft[0]);
    }

    public Point2D getDropZoneCenterRelativeToLocal(DropZone dz) {
        if (RootPane == null) {
            RootPane = (AnchorPane) root.getParent();
            assert RootPane != null;
        }

        double x = dz.getWidth() / 2;
        double y = dz.getHeight() / 2;

        Point2D scene = dz.localToScene(x,y);
        Point2D local = RootPane.sceneToLocal(scene);

        return new Point2D(local.getX() - dz.getPrefWidth(), local.getY() - dz.getPrefHeight());
    }

    private Point2D getCurrentPosition() {
        return new Point2D(root.getLayoutX(), root.getLayoutY());
    }

    private Point2D dzIndexToCoordinate(Point2D p) {
        // Find the index of the dropzone
        int i = 0;
        i += (int) p.getX() - 1;
        i += (int) (p.getY() - 1) * 5;
        DropZone dz = dropZone[i];

        return getDropZoneCenterRelativeToLocal(dz);
    }

    private List<Point2D> dzIndexToCoordinate(List<Point2D> p) {
        List<Point2D> result = new ArrayList<>();
        for (Point2D point : p) {
            result.add(dzIndexToCoordinate(point));
        }
        return result;
    }

    public void runAnimations(List<Point2D> p) {

        var dz = dzIndexToCoordinate(p);
        double x = dz.get(0).getX();
        root.setLayoutX(x);
        root.setLayoutY(-100);
        BearImage.setImage(imageBearDown[0]);

        Animation bearFall = animateBearFall(dz.get(0));
        Animation bear = animateBear(dz);
        Animation shockWave = animateShockWave();

        setStartAnimation(true);


        animations.add(bearFall);
        animations.add(shockWave);
        animations.add(bear);
        animations.add(animateLeave());

        while (!animations.isEmpty()) {
            if (isStartAnimation()){
                currentAnimation = animations.remove(0);
                currentAnimation.play();
                startAnimation = false;
            }
        }

        ladang.resetLadangColor();
    }

    private Animation animateBearFall(Point2D p) {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        final long[] previousTime = {System.nanoTime()};
        BearImage.setImage(imageBearDown[0]);
        float speed = 300.0f;

        KeyFrame keyFrame = new KeyFrame(Duration.millis((double) 1000 / 18), event -> {
            long currentTime = System.nanoTime();
            double deltaTime = (currentTime - previousTime[0]) / 1_000_000_000.0;
            previousTime[0] = currentTime;

            Point2D currentPosition = getCurrentPosition();

            if (currentPosition.getY() < p.getY()) {
                root.setLayoutY(root.getLayoutY() + speed * deltaTime);
                changeImageDown(BearImage);
            } else {
                root.setLayoutY(p.getY());
                BearImage.setImage(imageBearRight[0]);
                timeline.stop();
                setStartAnimation(true);
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        return timeline;
    }

    private Animation animateShockWave() {
        ShockWaveImage.setImage(imageShockWave[0]);

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        final long[] previousTime = {System.nanoTime()};

        KeyFrame keyFrame = new KeyFrame(Duration.millis(125), event -> {
            long currentTime = System.nanoTime();
            previousTime[0] = currentTime;
            changeImageShockWave(ShockWaveImage);
            if (currentShockWaveIndex == 0) {
                timeline.stop();
                setStartAnimation(true);
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        return timeline;
    }


    public Animation animateBear(List<Point2D> path) {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        final long[] previousTime = {System.nanoTime()};
        final boolean[] starter = {true};
        KeyFrame keyFrame = new KeyFrame(Duration.millis((double) 1000 / 12), event -> {
            if (starter[0]) {
                previousTime[0] = System.nanoTime();
                starter[0] = false;
            }

            long currentTime = System.nanoTime();
            double deltaTime = (currentTime - previousTime[0]) / 1_000_000_000.0;
            previousTime[0] = currentTime;

            if (path.isEmpty()) {
                timeline.stop();
                setStartAnimation(true);
                return;
            }

            Point2D point = path.get(0);
            Point2D currentPosition = getCurrentPosition();

            if (isInRange(currentPosition, point)) {
                updatePosition(point);
                clearDropZoneChildren(point);
                path.remove(0);

                if (path.isEmpty()) {
                    timeline.stop();
                    setStartAnimation(true);
                    return;
                }
            }

            updateImageAndPosition(currentPosition, point, deltaTime);
        });

        timeline.getKeyFrames().add(keyFrame);
        return timeline;
    }

    private boolean isInRange(Point2D currentPosition, Point2D point) {
        return Math.abs(currentPosition.getX() - point.getX()) <= range && Math.abs(currentPosition.getY() - point.getY()) <= range;
    }

    private void updatePosition(Point2D point) {
        root.setLayoutX(point.getX());
        root.setLayoutY(point.getY());
    }

    private void clearDropZoneChildren(Point2D point) {
        for (DropZone dz : dropZone) {
            if (!dz.isTarget()) continue;
            Bounds bounds = dz.localToScene(dz.getBoundsInLocal());
            if (bounds.contains(point.getX() + dz.getPrefWidth(), point.getY() + dz.getPrefHeight())) {
                CardUI card = (CardUI) dz.getChildren().get(0);

                // IF the card doesnt have effect trap then continue

                if (card.getCardData() != null) {
                    // TODO: Implement the effect of the card
                    cancelAllAnimation();
                }

                dz.getChildren().clear();
            }
        }
    }

    private void cancelAllAnimation(){
        currentAnimation.stop();
        animations.forEach(Animation::stop);
        animations.clear();
        root.setVisible(false);
        // Add the bear to the active deck
        GameWindowController.addCard(new AnimalCard("BERUANG", 0, 25, AnimalType.OMNIVORE, (ProductCard) getCard("DAGING_BERUANG")));
    }


    private void updateImageAndPosition(Point2D currentPosition, Point2D point, double deltaTime) {
        float speed = 150.0f;
        if (Math.abs(currentPosition.getX() - point.getX()) >= range) {
            if (currentPosition.getX() < point.getX()) {
                changeImageRight(BearImage);
                root.setLayoutX(root.getLayoutX() + speed * deltaTime);
            } else {
                changeImageLeft(BearImage);
                root.setLayoutX(root.getLayoutX() - speed * deltaTime);
            }
        } else if (Math.abs(currentPosition.getY() - point.getY()) >= range) {
            if (currentPosition.getY() < point.getY()) {
                changeImageDown(BearImage);
                root.setLayoutY(root.getLayoutY() + speed * deltaTime);
            } else {
                changeImageUp(BearImage);
                root.setLayoutY(root.getLayoutY() - speed * deltaTime);
            }
        }
    }

    public Animation animateLeave(){
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        final long[] previousTime = {System.nanoTime()};
        final boolean[] starter = {true};
        final float speed = 500.0f;
        KeyFrame keyFrame = new KeyFrame(Duration.millis((double) 1000 / 6), event -> {
            if (starter[0]) {
                previousTime[0] = System.nanoTime();
                starter[0] = false;
            }
            long currentTime = System.nanoTime();
            double deltaTime = (currentTime - previousTime[0]) / 1_000_000_000.0;
            previousTime[0] = currentTime;

            Point2D currentPosition = getCurrentPosition();

            if (currentPosition.getY() < -500) {
                timeline.stop();
                setStartAnimation(true);
            } else {
                root.setLayoutY(root.getLayoutY() - speed * deltaTime);
                changeImageUp(BearImage);
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        return timeline;
    }

    // Method to change the image
    private void changeImageUp(ImageView image) {
        image.setImage(imageBearUp[currentImageIndex]);
        currentImageIndex = (currentImageIndex + 1) % imageBearUp.length;
    }

    private void changeImageDown(ImageView image) {
        image.setImage(imageBearDown[currentImageIndex]);
        currentImageIndex = (currentImageIndex + 1) % imageBearDown.length;
    }

    private void changeImageLeft(ImageView image) {
        image.setImage(imageBearLeft[currentImageIndex]);
        currentImageIndex = (currentImageIndex + 1) % imageBearLeft.length;
    }

    private void changeImageRight(ImageView image) {
        image.setImage(imageBearRight[currentImageIndex]);
        currentImageIndex = (currentImageIndex + 1) % imageBearRight.length;
    }

    private void changeImageShockWave(ImageView image) {
        image.setImage(imageShockWave[currentShockWaveIndex]);
        currentShockWaveIndex = (currentShockWaveIndex + 1) % imageShockWave.length;
    }
}
