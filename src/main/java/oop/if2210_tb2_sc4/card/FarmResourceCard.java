package oop.if2210_tb2_sc4.card;

import java.util.List;

public abstract class FarmResourceCard extends Card{
    ProductCard productResult;
    List<EffectType> effectApplied;

    public FarmResourceCard(String name) {
        super(name);
        this.productResult = null;
        this.effectApplied = null;
    }

    public FarmResourceCard(String name, ProductCard productResult) {
        super(name);
        this.productResult = productResult;
        this.effectApplied = null;
    }

    public ProductCard getProductResult() {
        return productResult;
    }

    public void setProductResult(ProductCard productResult) {
        this.productResult = productResult;
    }

    public void addEffect(EffectType effectApplied) {
        this.effectApplied.add(effectApplied);
    }

    public void removeEffect(EffectType effectToRemove) {
        this.effectApplied.remove(effectToRemove);
    }

    public List<EffectType> getEffect() {
        return effectApplied;
    }

    public boolean isProtected() {
        return effectApplied.contains(EffectType.PROTECTION);
    }

    public boolean isDelayed() {
        return effectApplied.contains(EffectType.DELAY);
    }

    public boolean isInstantHarvest() {
        return effectApplied.contains(EffectType.INSTANT_HARVEST);
    }

    public boolean isTrapActivated() {
        return effectApplied.contains(EffectType.TRAP);
    }

    public boolean isAccelerated() {
        return effectApplied.contains(EffectType.ACCELERATE);
    }

    public ProductCard harvest() {
        return new ProductCard(this.productResult);
    }
}
