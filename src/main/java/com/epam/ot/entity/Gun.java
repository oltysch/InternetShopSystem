package com.epam.ot.entity;

import java.util.Map;

public class Gun extends Product {
    private String origin;
    private String type;
    private Integer firingRange;
    private Integer effectiveFiringRange;
    private Integer magazineCapacity;
    private String caliber;
    private Integer fireRate;

    public Gun(String type, String model, Double price) {
        super(model, price);
        this.type = type;
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

    public Integer getFiringRange() {
        return firingRange;
    }

    public void setFiringRange(Integer firingRange) {
        this.firingRange = firingRange;
    }

    public Integer getFireRate() {
        return fireRate;
    }

    public void setFireRate(Integer fireRate) {
        this.fireRate = fireRate;
    }

    public Integer getEffectiveFiringRange() {
        return effectiveFiringRange;
    }

    public void setEffectiveFiringRange(Integer effectiveFiringRange) {
        this.effectiveFiringRange = effectiveFiringRange;
    }

    public Integer getMagazineCapacity() {
        return magazineCapacity;
    }

    public void setMagazineCapacity(Integer magazineCapacity) {
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
        block.setDescription(getDescription());
        block.setPrice(getPrice());
        if (origin != null && !origin.equals("")) {
            block.addCharacteristics("gun.origin", origin);
        }
        if (type != null && !type.equals("")) {
            block.addCharacteristics("gun.type", type);
        }
        if (firingRange != null && firingRange > 0) {
            block.addCharacteristics("gun.firingRange", String.valueOf(firingRange));
        }
        if (effectiveFiringRange != null && effectiveFiringRange > 0) {
            block.addCharacteristics("gun.effectiveFiringRange", String.valueOf(effectiveFiringRange));
        }
        if (magazineCapacity != null && magazineCapacity > 0) {
            block.addCharacteristics("gun.magazineCapacity", String.valueOf(magazineCapacity));
        }
        if (caliber != null && !caliber.equals("")) {
            block.addCharacteristics("gun.caliber", caliber);
        }
        if (fireRate != null && fireRate > 0) {
            block.addCharacteristics("gun.fireRate", String.valueOf(fireRate));
        }
        return block;
    }

    public enum Handy {One_handed, Two_handed;}
}
