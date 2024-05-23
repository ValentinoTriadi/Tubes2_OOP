package oop.if2210_tb2_sc4.UI;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SeranganBeruangUI {

    @FXML
    public ImageView BearImage;
    public ImageView ShockWaveImage;
    public Pane root;

    private int currentImageIndex = 0;
    private int currentShockWaveIndex = 0;

    private final Image[] imageBearUp = new Image[3];
    private final Image[] imageBearDown = new Image[3];
    private final Image[] imageBearLeft = new Image[3];
    private final Image[] imageBearRight = new Image[3];
    private final Image[] imageShockWave = new Image[8];

    private DropZone[] dropZone;
    private double x, y;
    private boolean startAnimation = false;

    private final float speed = 300.0f;
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

    private Point2D getDropZoneCenterRelativeToRoot(DropZone dz) {
        Bounds dropzoneBounds = dz.localToScene(dz.getBoundsInLocal());
        return new Point2D(dropzoneBounds.getMinX() + dropzoneBounds.getWidth() / 2, dropzoneBounds.getMinY() + dropzoneBounds.getHeight() / 2);
    }

    private Point2D getCurrentPositionRelativeToRoot() {
        Bounds rootBounds = root.localToScene(root.getBoundsInLocal());
        return new Point2D(rootBounds.getMinX(), rootBounds.getMinY());
    }

    private Point2D dzIndexToCoordinate(Point2D p) {
        int i = 0;
        for (int j = 0; j < p.getX(); j++) {
            i += 5 - j;
        }
        DropZone dz = dropZone[i + (int) p.getX()];
        return getDropZoneCenterRelativeToRoot(dz);
    }

    private List<Point2D> dzIndexToCoordinate(List<Point2D> p) {
        List<Point2D> result = new ArrayList<>();
        for (Point2D point : p) {
            result.add(dzIndexToCoordinate(point));
        }
        return result;
    }

    public void runAnimations(List<Point2D> p) {
        BearImage.setX(dzIndexToCoordinate(p.get(0)).getX());
        BearImage.setY(-100);
        BearImage.setImage(imageBearDown[0]);

        Animation bearFall = animateBearFall(dzIndexToCoordinate(p.get(0)));
        Animation bear = animateBear(dzIndexToCoordinate(p));
        Animation shockWave = animateShockWave();

        setStartAnimation(true);

        List<Animation> animations = new ArrayList<>();
        animations.add(bearFall);
        animations.add(shockWave);
        animations.add(bear);

        while (!animations.isEmpty()) {
            if (isStartAnimation()){
                animations.get(0).play();
                animations.remove(0);
                startAnimation = false;
            }
        }
    }

    private Animation animateBearFall(Point2D p) {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE); // Run only once

        final long[] previousTime = {System.nanoTime()};
        BearImage.setImage(imageBearDown[0]);

        KeyFrame keyFrame = new KeyFrame(Duration.millis((double) 1000 / 60), event -> {
            long currentTime = System.nanoTime();
            double deltaTime = (currentTime - previousTime[0]) / 1_000_000_000.0;
            previousTime[0] = currentTime;

            Point2D currentPosition = getCurrentPositionRelativeToRoot();

            if (currentPosition.getY() < p.getY()) {
                System.out.println("Current position: " + currentPosition + " Target position: " + p);
                root.setLayoutY(currentPosition.getY() + speed * deltaTime);
            } else {
                root.setLayoutY(p.getY()); // Ensure the bear reaches the exact target position
                BearImage.setImage(imageBearRight[0]);
                timeline.stop();
                setStartAnimation(true);
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        return timeline;
    }

    private Animation animateShockWave() {
        BearImage.setImage(imageShockWave[0]);

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE); // Run only once

        final long[] previousTime = {System.nanoTime()};

        KeyFrame keyFrame = new KeyFrame(Duration.millis(125), event -> {
            long currentTime = System.nanoTime();
            previousTime[0] = currentTime;
            changeImageShockWave(ShockWaveImage);
            System.out.println("Current shockwave index: " + currentShockWaveIndex);

            if (currentShockWaveIndex == 0) {
                // Use a PauseTransition to signal the end of this animation
                timeline.stop();
                setStartAnimation(true);
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        return timeline;
    }

    public Animation animateBear(List<Point2D> path) {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE); // Run only once

        final long[] previousTime = {System.nanoTime()};

        KeyFrame keyFrame = new KeyFrame(Duration.millis((double) 1000 / 60), event -> {
            long currentTime = System.nanoTime();
            double deltaTime = (currentTime - previousTime[0]) / 1_000_000_000.0;
            previousTime[0] = currentTime;

            if (path.isEmpty()) {
                // Use a PauseTransition to signal the end of this animation
                timeline.stop();
                setStartAnimation(true);
                return;
            }

            Point2D point = path.get(0);
            Point2D currentPosition = getCurrentPositionRelativeToRoot();

            System.out.println("Bear");
            System.out.println("Current position: " + currentPosition + " Target position: " + point);

            if (currentPosition.equals(point)) {
                path.remove(0);
                if (path.isEmpty()) {
                    // Use a PauseTransition to signal the end of this animation
                    timeline.stop();
                    setStartAnimation(true);
                    return;
                }
                point = path.get(0);
            }

            if (currentPosition.getX() < point.getX()) {
                changeImageRight(BearImage);
                root.setLayoutX(currentPosition.getX() + speed * deltaTime);
            } else if (currentPosition.getX() > point.getX()) {
                changeImageLeft(BearImage);
                root.setLayoutX(currentPosition.getX() - speed * deltaTime);
            } else if (currentPosition.getY() < point.getY()) {
                changeImageDown(BearImage);
                root.setLayoutY(currentPosition.getY() + speed * deltaTime);
            } else {
                changeImageUp(BearImage);
                root.setLayoutY(currentPosition.getY() - speed * deltaTime);
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
