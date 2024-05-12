package oop.if2210_tb2_sc4.util;

import javafx.scene.image.Image;
import oop.if2210_tb2_sc4.UI.CardUI;
import oop.if2210_tb2_sc4.card.AnimalCard;
import oop.if2210_tb2_sc4.card.Card;
import oop.if2210_tb2_sc4.card.PlantCard;
import oop.if2210_tb2_sc4.card.ProductCard;

import java.util.Objects;

public class ImageUtil {
    private static String CardFolderType(Card card){
        if(card instanceof AnimalCard){
            return "Hewan";
        }else if(card instanceof PlantCard){
            return "Tanaman";
        }else if(card instanceof ProductCard){
            return "Produk";
        }else{
            return "Item";
        }
    }
    public static Image getCardImage(Card card){
        String folderPath = "assets/card/";
        String folderName = CardFolderType(card);
        String path = folderPath + folderName + "/" + card.getName() + ".png";
        // Card UI are used just to get the correct Path
        // Don't change the path at all cost
        return new Image(Objects.requireNonNull(CardUI.class.getResource(path)).toExternalForm());
    }
}
