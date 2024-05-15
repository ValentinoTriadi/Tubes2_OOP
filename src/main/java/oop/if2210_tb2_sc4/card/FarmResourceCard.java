package oop.if2210_tb2_sc4.card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class FarmResourceCard extends Card{
    protected ProductCard productResult;
    List<EffectType> effectApplied;

    public FarmResourceCard(String name) {
        super(name);
        this.productResult = null;
        this.effectApplied = new ArrayList<>();
    }

    public FarmResourceCard(String name, ProductCard productResult) {
        super(name);
        this.productResult = productResult;
        this.effectApplied = new ArrayList<>();
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
        return effectApplied.contains(EffectType.PROTECT);
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

    public Map<String, Integer> countEffectTypes() {
        Map<EffectType, Integer> countMap = new HashMap<>();
        for (EffectType type : EffectType.values()) {
            countMap.put(type, 0);
        }

        for (EffectType effect : effectApplied) {
            countMap.put(effect, countMap.getOrDefault(effect, 0) + 1);
        }

        Map<String, Integer> result = new HashMap<>();
        for (Map.Entry<EffectType, Integer> entry : countMap.entrySet()) {
            result.put(entry.getKey().toString(), entry.getValue());
        }

        return result;
    }
}
