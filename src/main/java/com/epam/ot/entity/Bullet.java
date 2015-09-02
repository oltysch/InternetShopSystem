package com.epam.ot.entity;

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
        ProductBlock block = new ProductBlock();
        block.setName(caliber + " " + getName());
        block.setProductType("bullet");
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
        if (caliber != null && !caliber.equals("")) {
            block.addCharacteristics("bullet.caliber", caliber);
        }
        if (type != null && !type.equals("")) {
            block.addCharacteristics("bullet.type", type);
        }
        if (qty != 0) {
            block.addCharacteristics("bullet.qty", String.valueOf(qty));
        }
        return block;
    }
}
