package oop.if2210_tb2_sc4.UI;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ShopItems {
    public Label productName;
    public ImageView Image;
    public Label labelHarga;
    public Label labelJumlah;

    public ShopItems(String productName, String Image, String labelHarga, String labelJumlah) {
        this.productName = new Label(productName);
        this.Image = new ImageView(Image);
        this.labelHarga = new Label(labelHarga);
        this.labelJumlah = new Label(labelJumlah);
    }

    public void setProductName(String productName) {
        this.productName.setText(productName);
    }

    public void setImage(String Image) {
        this.Image.setImage(new javafx.scene.image.Image(Image));
    }

    public void setLabelHarga(String labelHarga) {
        this.labelHarga.setText(labelHarga);
    }

    public void setLabelJumlah(String labelJumlah) {
        this.labelJumlah.setText(labelJumlah);
    }
}
