package com.epam.ot.entity;

import java.util.Map;

public class Gun extends Product {
    private String origin;
    private String type;
    private int firingRange;
    private int effectiveFiringRange;
    private int magazineCapacity;
    private String caliber;
    private int fireRate;

    public Gun(String type, String model, Double price, String origin, String caliber, int magazineCapacity,
               int fireRate, int firingRange, int effectiveFiringRange) {
        super(model, price);
        this.origin = origin;
        this.type = type;
        this.caliber = caliber;
        this.magazineCapacity = magazineCapacity;
        this.fireRate = fireRate;
        this.firingRange = firingRange;
        this.effectiveFiringRange = effectiveFiringRange;
    }

    public Gun() {
        super();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getFiringRange() {
        return firingRange;
    }

    public void setFiringRange(int firingRange) {
        this.firingRange = firingRange;
    }

    public int getFireRate() {
        return fireRate;
    }

    public void setFireRate(int fireRate) {
        this.fireRate = fireRate;
    }

    public int getEffectiveFiringRange() {
        return effectiveFiringRange;
    }

    public void setEffectiveFiringRange(int effectiveFiringRange) {
        this.effectiveFiringRange = effectiveFiringRange;
    }

    public int getMagazineCapacity() {
        return magazineCapacity;
    }

    public void setMagazineCapacity(int magazineCapacity) {
        this.magazineCapacity = magazineCapacity;
    }

    public String getCaliber() {
        return caliber;
    }

    public void setCaliber(String caliber) {
        this.caliber = caliber;
    }

    @Override
    public ProductBlock toBlock() {
        ProductBlock block = new ProductBlock();
        block.setName(getName() + " " + caliber);
        block.setProductType("gun");
        block.setUuid(getUuid());
        String gettingShortDescription = getDescription();
        block.setFullDescription(getDescription());
        if (gettingShortDescription == null) {
            gettingShortDescription = "Описание отсутствует";
            block.setShortDescription(gettingShortDescription);
            block.setFullDescription("");
        } else if (gettingShortDescription.length() > 101) {
            gettingShortDescription = gettingShortDescription.substring(0, 100) + "...";
            block.setShortDescription(gettingShortDescription);
        }
        block.setPrice(getPrice());
        if (origin != null && !origin.equals("")) {
            block.addCharacteristics("gun.origin", origin);
        }
        if (type != null && !type.equals("")) {
            block.addCharacteristics("gun.type", type);
        }
        if (firingRange != 0) {
            block.addCharacteristics("gun.firingRange", String.valueOf(firingRange));
        }
        if (effectiveFiringRange != 0) {
            block.addCharacteristics("gun.effectiveFiringRange", String.valueOf(effectiveFiringRange));
        }
        if (magazineCapacity != 0) {
            block.addCharacteristics("gun.magazineCapacity", String.valueOf(magazineCapacity));
        }
        if (caliber != null && !caliber.equals("")) {
            block.addCharacteristics("gun.caliber", caliber);
        }
        if (fireRate != 0) {
            block.addCharacteristics("gun.fireRate", String.valueOf(fireRate));
        }
        return block;
    }

    public enum Handy {One_handed, Two_handed;}
}
