package com.epam.ot.entity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "", propOrder = {
        "caliber",
        "type",
        "qty"
})
@XmlRootElement(name = "bullet")
public class Bullet extends Product {
    private String caliber;
    private String type;
    private int qty;

    public Bullet() {
        super();
    }

    public Bullet(String caliber, String name, String bulletType, Double price, int qty) {
        super(name, price);
        this.caliber = caliber;
        this.type = bulletType;
        this.qty = qty;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String getName() {
        return caliber + " " + super.getName();
    }

    @Override
    public ProductBlock toBlock() {
        return null;
    }
}
