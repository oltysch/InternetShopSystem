package com.epam.ot.products;

public class Bullet extends Product {
    private String caliber;
    private String type;

    public Bullet(String caliber, String name, String bulletType, Double price) {
        super(name, price);
        this.caliber = caliber;
        this.type = bulletType;
    }

    public String getCaliber() {
        return caliber;
    }

    public void setCaliber(String caliber) {
        this.caliber = caliber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //TODO check usefulness of this idea
    @Override
    public String getName() {
        return caliber + " " + super.getName();
    }
}
