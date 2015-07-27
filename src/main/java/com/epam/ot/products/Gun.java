package com.epam.ot.products;

public class Gun extends Product {
    private String origin;
    private Handy handy;
    private int firingRange;
    private int effectiveFiringRange;
    private Boolean cartridgeClipAvailability;
    private Boolean opticsAvailability;
    private String material;

    public Gun(long id, String model, Double price, String origin, String handy, int firingRange, int effectiveFiringRange,
               Boolean cartridgeClip, Boolean optics, String material) {
        super(id, model, price);
        this.origin = origin;
        this.handy = Handy.valueOf(handy);
        this.firingRange = firingRange;
        this.effectiveFiringRange = effectiveFiringRange;
        this.cartridgeClipAvailability = cartridgeClip;
        this.opticsAvailability = optics;
        this.material = material;
    }

    public Gun() {
        super();
    }

    @Override
    public String toString() {
        return getModel() + " " + origin + " " + handy + " " + material + "\n     FR: " + firingRange + " EFR: " + effectiveFiringRange + " CC: " + cartridgeClipAvailability + " Opt.: " + opticsAvailability;
    }

    //TODO - need? or no?
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

    public String getHandy() {
        return handy.toString();
    }

    public void setHandy(String handy) {
        this.handy = Handy.valueOf(handy);
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

    public Boolean getCartridgeClipAvailability() {
        return cartridgeClipAvailability;
    }

    public void setCartridgeClipAvailability(Boolean cartridgeClipAvailability) {
        this.cartridgeClipAvailability = cartridgeClipAvailability;
    }

    //TODO - need? or no?
    public long getId() {
        return super.getId();
    }

    //TODO - need? or no?
    public void setId(long id) {
        super.setId(id);
    }

    public Boolean getOpticsAvailability() {
        return opticsAvailability;
    }

    public void setOpticsAvailability(Boolean opticsAvailability) {
        this.opticsAvailability = opticsAvailability;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public enum Handy {One_handed, Two_handed;}
}
