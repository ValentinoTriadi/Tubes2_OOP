package oop.if2210_tb2_sc4.UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import oop.if2210_tb2_sc4.card.*;
import oop.if2210_tb2_sc4.util.ImageUtil;
import oop.if2210_tb2_sc4.util.StringUtil;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;


public class HarvestedPanelController {
    public Label information;
    public Label activeItem;
    public Button panenButton;
    private AnchorPane root;
    public Label productname;
    public ImageView imageView;
    public Card card;

    public void setCard(Card card) {
        this.card = card;
    }


    public void setImage(Card cardData) {
        Image image = ImageUtil.getCardImage(cardData);
        imageView.setImage(image);
    }

    public void setProductName(Card cardData){
        productname.setText(StringUtil.toTitleCase(cardData.getName()));
    }

    public void setInformation(Card cardData){
        if(cardData instanceof AnimalCard){
            information.setText("Berat : " + ((AnimalCard) cardData).getWeight() + "(" + ((AnimalCard) cardData).getHarvestWeight() + ")");
        } else {
            information.setText("Umur : " + ((PlantCard) cardData).getAge() + "(" + ((PlantCard) cardData).getHarvestAge() + ")");
        }
        information.toFront();
    }

    public void setRoot(AnchorPane root) {
        this.root = root;
    }

    public void setActiveItem(Card cardData) {
        StringBuilder activeItemBuilder = new StringBuilder();
        activeItemBuilder.append("Item aktif : ");

        if (cardData instanceof FarmResourceCard) {
            Map<String, Integer> effectTypes = ((FarmResourceCard) cardData).countEffectTypes();

            for (Map.Entry<String, Integer> entry : effectTypes.entrySet()) {
                String effectName = entry.getKey();
                int effectCount = entry.getValue();

                if (effectCount > 0) {
                    if (activeItemBuilder.length() > 0) {
                        activeItemBuilder.append(", ");
                    }
                    activeItemBuilder.append(effectName).append(" (").append(effectCount).append(")");
                }
            }
        }


        String activeItemString = activeItemBuilder.toString();
        activeItem.setText(activeItemString);
    }


    public void close(MouseEvent mouseEvent) throws IOException {
        System.out.println("close");
        GameWindowController.rootStatic.getChildren().remove(root);
    }

    public void handlePanenButton(Card card){
        if(card instanceof AnimalCard && ((AnimalCard) card).isHarvestable()){
            panenButton.setDisable(false);
            GameWindowController.addItem(card);
        } else if(card instanceof PlantCard && ((PlantCard) card).isHarvestable()){
            panenButton.setDisable(false);
            GameWindowController.addItem(card);
        } else{
            panenButton.setDisable(true);
        }
    }

    public void panen(MouseEvent mouseEvent) {
        handlePanenButton(card);
    }

}
