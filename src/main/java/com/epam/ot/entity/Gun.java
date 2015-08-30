package com.epam.ot.entity;

import javax.xml.bind.annotation.*;

@XmlType(name = "", propOrder = {
        //TODO fix xsd and this
        "type",
        "origin",
        "firingRange",
        "effectiveFiringRange",
        "magazineCapacity",
        "caliber",
        "fireRate"})
@XmlRootElement(name = "gun")
public class Gun extends Product {
    @XmlElement
    private String origin;
    @XmlElement(required = true)
    private String type;
    @XmlElement
    //TODO make metric class
    private int firingRange;
    @XmlElement
    private int effectiveFiringRange;
    @XmlElement
    private int magazineCapacity;
    //TODO make caliber class
    @XmlElement
    private String caliber;
    //TODO make fire rate class
    @XmlElement
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

    public enum Handy {One_handed, Two_handed;}

    @Override
    public ProductBlock toBlock() {
        ProductBlock block = new ProductBlock();
        block.setName(getName() + " " + caliber);
        String gettingShortDescription = getDescription();
        if (gettingShortDescription == null) {
            gettingShortDescription = "Описание отсутствует";
        } else if (gettingShortDescription.length() > 101) {
            gettingShortDescription = gettingShortDescription.substring(0, 100) + "...";
        }
        block.setShortDescription(gettingShortDescription);
        block.setPrice(getPrice());
        block.setFullDescription("");
        block.setSimpleDescription("");
        return block;
    }
}
