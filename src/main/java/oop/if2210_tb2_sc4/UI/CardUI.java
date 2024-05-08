package oop.if2210_tb2_sc4.UI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class CardUI extends DraggablePane {

    private String name;

    public CardUI(Pane parent, DropZone[] dropZone) {
        super(parent, dropZone);
        setPrefSize(100, 120);
        setStyle("-fx-background-color: white;");
    }

    public void initParent(Pane parent){
        this.parent = parent;
        setLayoutX(0);
        setLayoutY(0);
    }

    private void setImage() {
        String path = "assets/card/" + name + ".png";
        Image image = new Image(Objects.requireNonNull(getClass().getResource(path)).toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(120);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        getChildren().add(imageView);
    }

    private void setName(String name) {
        this.name = name;
    }

    public void initCard(String name){
        setName(name);
        setImage();
    }
}
