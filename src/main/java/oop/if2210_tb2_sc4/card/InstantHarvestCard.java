package oop.if2210_tb2_sc4.card;

import oop.if2210_tb2_sc4.Exception.FullActiveHandsException;
import oop.if2210_tb2_sc4.UI.DropZone;
import oop.if2210_tb2_sc4.UI.GameWindowController;

public class InstantHarvestCard extends GoodPotion {
    private DropZone harvestedCardContainer;

    public InstantHarvestCard(String name) {
        super(name);
    }

    public InstantHarvestCard(InstantHarvestCard instantHarvestCard) {
        super(instantHarvestCard.getName());
    }

    public void setHarvestedCardContainer(DropZone dz){
        harvestedCardContainer = dz;
    }

    @Override
    public void applyEffect(FarmResourceCard card) {
        try {
            ProductCard cardHarvest = card.getProductResult();
            GameWindowController.getCurrentPlayerPane().addItem(cardHarvest);
            harvestedCardContainer.getChildren().remove(0);
        } catch (FullActiveHandsException e) {
            e.ShowErrorPanel();
        }
    }
}
