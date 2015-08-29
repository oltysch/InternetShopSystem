package com.epam.ot.entity;

import javax.xml.bind.annotation.*;

@XmlType(name = "", propOrder = {
        //TODO fix xsd and this
        "type",
        "model",
        "origin"})
@XmlRootElement(name = "gun")
public class Gun extends Product {
    @XmlElement
    private String origin;
    @XmlElement
    private String type;
    //TODO make metric class
    /*@XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "TTC", propOrder = {
            "caliber",
            "magazineCapacity",
            "fireRate",
            "firingRange",
            "effectiveFiringRange"
    })*/
    @XmlElementWrapper(name = "TTC")
    @XmlElement
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

    @Override
    public String toString() {
        return type + ": " + getModel() + " " + origin + "\n    Cal.: " + caliber + " x " + magazineCapacity + ", fRate: " + fireRate + " FR: " + firingRange + " EffectiveFR: " + effectiveFiringRange;
    }

    @XmlElement(name = "model")
    public String getModel() {
        return super.getName();
    }

    public void setModel(String model) {
        setName(model);
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

    public enum Handy {One_handed, Two_handed;}

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
}
