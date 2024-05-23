package oop.if2210_tb2_sc4.UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import oop.if2210_tb2_sc4.Exception.FullActiveHandsException;
import oop.if2210_tb2_sc4.card.*;
import oop.if2210_tb2_sc4.deck.Deck;
import oop.if2210_tb2_sc4.ladang.Ladang;
import oop.if2210_tb2_sc4.util.ImageUtil;
import oop.if2210_tb2_sc4.util.StringUtil;


import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.EnumMap;



public class HarvestedPanelController {
    public Label information;
    public Label activeItem;
    public Button panenButton;
    private AnchorPane root;
    public Label productname;
    public ImageView imageView;
    public Card card;
    private DropZone dropZones;

    public void setCard(Card card) {
        this.card = card;
    }

    public void setDropZones(DropZone dropZones) {
        this.dropZones = dropZones;
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
        activeItemBuilder.append("Item aktif: ");

        if (cardData instanceof FarmResourceCard) {
            List<EffectType> effectTypes = ((FarmResourceCard) cardData).getEffect();

            Map<EffectType, Integer> effectTypeCounts = new EnumMap<>(EffectType.class);
            for (EffectType effectType : effectTypes) {
                effectTypeCounts.put(effectType, effectTypeCounts.getOrDefault(effectType, 0) + 1);
            }


            boolean first = true;
            for (Map.Entry<EffectType, Integer> entry : effectTypeCounts.entrySet()) {
                EffectType effectType = entry.getKey();
                int count = entry.getValue();
                if (count > 0) {
                    if (!first) {
                        activeItemBuilder.append(", ");
                    } else {
                        first = false;
                    }
                    activeItemBuilder.append(effectType.name()).append(" (").append(count).append(")");
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

        } else if(card instanceof PlantCard && ((PlantCard) card).isHarvestable()){
            panenButton.setDisable(false);
        } else {
            panenButton.setDisable(true);
        }
    }

    public void panen(MouseEvent mouseEvent) {
        Deck activeDeck = GameWindowController.getCurrentPlayerPane().getDeckUI().getDeckData();
       try{
           ProductCard cardHarvest = ((FarmResourceCard)card).getProductResult();
           GameWindowController.getCurrentPlayerPane().addItem(cardHarvest);
           this.dropZones.getChildren().remove(0);
       }catch (FullActiveHandsException e){
           e.ShowErrorPanel();
       }
        GameWindowController.rootStatic.getChildren().remove(root);
    }

}
