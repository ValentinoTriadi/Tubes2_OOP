package oop.if2210_tb2_sc4.UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import oop.if2210_tb2_sc4.util.ImageUtil;
import oop.if2210_tb2_sc4.util.StringUtil;

import java.util.Objects;

public class ShopItems {

    private final String productName;
    private final Image Image;
    private ShopItemsController controller;
    private String labelHarga;
    private String labelJumlah;
    private AnchorPane scene;

    public String getLabelJumlah(){
        return labelJumlah;
    }
    public  String getLabelHarga(){
        return labelHarga;
    }
    public String getProductName(){
        return StringUtil.toUpperSnakeCase(productName);
    }
    public void setLabelHarga(int money){
        labelHarga = String.valueOf(money);
    }

    public void setLabelJumlah(int amount){
        labelJumlah = String.valueOf(amount);
        controller.labelJumlahUI.setText(labelJumlah);
    }

    public void reduceJumlah(){
        int jumlah = Integer.parseInt(labelJumlah);
        setLabelJumlah(jumlah - 1);
    }
    public void addJumlah(int amount){
        int jumlah = Integer.parseInt(labelJumlah);
        setLabelJumlah(jumlah + amount);
    }

    public ShopItems(String productName, Image Image, String labelHarga, String labelJumlah) {
        this.productName = productName;
        this.Image = Image;
        this.labelHarga = labelHarga;
        this.labelJumlah = labelJumlah;
        initialize();
    }

    public void initialize(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShopItems.fxml"));
        try {
            scene = fxmlLoader.load();
            controller = fxmlLoader.getController();
            controller.productNameUI = productName;
            controller.labelHargaUI.setText(labelHarga);
            controller.ImageUI.setImage(Image);
            controller.labelJumlahUI.setText(labelJumlah);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public AnchorPane getScene() {
        return scene;
    }


}
