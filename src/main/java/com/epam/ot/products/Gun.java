package com.epam.ot.products;

import javax.xml.bind.annotation.*;

@XmlType(name = "", propOrder = {
        "model",
        "origin",
        "handy",
        "ttc",
        "material"})
@XmlRootElement(name = "gun")
public class Gun extends Product {
    @XmlElement
    private String origin;
    @XmlElement
    private Handy handy;
    @XmlElement(name = "TTC")
    private Gun.Ttc ttc;
    @XmlElement
    private String material;

    public Gun(long id, String model, Double price, String origin, Handy handy, int firingRange, int effectiveFiringRange,
               Boolean cartridgeClip, Boolean optics, String material) {
        super(id, model, price);
        this.origin = origin;
        this.handy = handy;
        this.material = material;
        ttc = new Ttc(firingRange, effectiveFiringRange, cartridgeClip, optics);
    }

    public Gun() {
        super();
    }

    @Override
    public String toString() {
//        return getName() + " " + origin + " " + handy + " " + material + "\n     FR: " + ttc.getFiringRange() + " EFR: " + ttc.getEffectiveFiringRange() + " CC: " + ttc.getCartridgeClipAvailability() + " Opt.: " + ttc.getOpticsAvailability();
        return getName() + " " + origin + " " + handy + " " + material + "\n     FR: " + ttc.getFiringRange() + " EFR: " + ttc.getEffectiveFiringRange() + " CC: " + ttc.getCartridgeClipAvailability() + " Opt.: " + ttc.getOpticsAvailability();
    }

    //TODO - need? or no?
    @XmlElement(name = "model")
    public String getModel() {
        return getName();
    }

    //TODO - need? or no?
    public void setModel(String model) {
        setName(model);
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

    public String getHandy() {
        return handy.toString();
    }

    public void setHandy(Handy handy) {
        this.handy = handy;
    }

    //TODO - need? or no?
    public long getId() {
        return super.getId();
    }

    //TODO - need? or no?
    public void setId(long id) {
        super.setId(id);
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public enum Handy {One_handed, Two_handed;}

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "TTC", propOrder = {
            "firingRange",
            "effectiveFiringRange",
            "cartridgeClipAvailability",
            "opticsAvailability"
    })
    public static class Ttc {
        private int firingRange;
        private int effectiveFiringRange;
        private boolean cartridgeClipAvailability;
        private boolean opticsAvailability;

        public Ttc() {
        }

        public Ttc(int firingRange, int effectiveFiringRange, boolean cartridgeClipAvailability, boolean opticsAvailability) {
            this.firingRange = firingRange;
            this.effectiveFiringRange = effectiveFiringRange;
            this.cartridgeClipAvailability = cartridgeClipAvailability;
            this.opticsAvailability = opticsAvailability;
        }

        public int getFiringRange() {
            return firingRange;
        }

        public void setFiringRange(int firingRange) {
            this.firingRange = firingRange;
        }

        public int getEffectiveFiringRange() {
            return effectiveFiringRange;
        }

        public void setEffectiveFiringRange(int effectiveFiringRange) {
            this.effectiveFiringRange = effectiveFiringRange;
        }

        public boolean getCartridgeClipAvailability() {
            return cartridgeClipAvailability;
        }

        public void setCartridgeClipAvailability(boolean cartridgeClipAvailability) {
            this.cartridgeClipAvailability = cartridgeClipAvailability;
        }

        public boolean getOpticsAvailability() {
            return opticsAvailability;
        }

        public void setOpticsAvailability(boolean opticsAvailability) {
            this.opticsAvailability = opticsAvailability;
        }
    }
}
