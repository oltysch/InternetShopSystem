package com.epam.ot.products;

import javax.xml.bind.annotation.*;

@XmlType(name = "", propOrder = {
        "type",
        "model",
        "origin",
        "ttc"})
@XmlRootElement(name = "gun")
public class Gun extends Product {
    @XmlElement
    private String origin;
    @XmlElement
    private String type;
    @XmlElement(name = "TTC")
    private Gun.Ttc ttc;

    public Gun(long id, String type, String model, Double price, String origin, String caliber, int magazineCapacity,
               int fireRate, int firingRange, int effectiveFiringRange) {
        super(id, model, price);
        this.origin = origin;
        this.type = type;
        ttc = new Ttc(caliber, magazineCapacity, fireRate, firingRange, effectiveFiringRange);
    }

    public Gun() {
        super();
    }

    @Override
    public String toString() {
        return type + ": " + getModel() + " " + origin + "\n    Cal.: " + ttc.caliber + " x " + ttc.magazineCapacity + ", fRate: " + ttc.fireRate + " FR: " + ttc.firingRange + " EffectiveFR: " + ttc.effectiveFiringRange;
    }

    //TODO - need? or no?
    @XmlElement(name = "model")
    public String getModel() {
        return super.getName();
    }

    //TODO - need? or no?
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

    public Ttc getTtc() {
        return ttc;
    }

    public void setTtc(Ttc ttc) {
        this.ttc = ttc;
    }

    public enum Handy {One_handed, Two_handed;}

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "TTC", propOrder = {
            "caliber",
            "magazineCapacity",
            "fireRate",
            "firingRange",
            "effectiveFiringRange"
    })
    public static class Ttc {
        //TODO make metric class
        private int firingRange;
        private int effectiveFiringRange;
        private int magazineCapacity;
        //TODO make caliber class
        private String caliber;
        //TODO make fire rate class
        private int fireRate;

        public Ttc() {
        }

        public Ttc(String caliber, int magazineCapacity, int fireRate, int firingRange, int effectiveFiringRange) {
            this.caliber = caliber;
            this.magazineCapacity = magazineCapacity;
            this.fireRate = fireRate;
            this.firingRange = firingRange;
            this.effectiveFiringRange = effectiveFiringRange;
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
    }
}
