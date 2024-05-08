package oop.if2210_tb2_sc4.UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class ShopItems {

    private final String productName;
    private final String Image;
    private final String labelHarga;
    private final String labelJumlah;
    private AnchorPane scene;

    public ShopItems(String productName, String Image, String labelHarga, String labelJumlah) {
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
            ShopItemsController controller = fxmlLoader.getController();
            controller.productNameUI.setText(productName);
            controller.labelHargaUI.setText(labelHarga);
            controller.labelJumlahUI.setText(labelJumlah);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AnchorPane getScene() {
        return scene;
    }


}
