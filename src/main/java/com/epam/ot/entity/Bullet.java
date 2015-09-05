package com.epam.ot.entity;

public class Bullet extends Product {
    private String caliber;
    private String type;
    private Integer qty;

    public Bullet() {
        super();
    }

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

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    @Override
    public ProductBlock toBlock() {
        ProductBlock block = new ProductBlock();
        block.setName(caliber + " " + getName());
        block.setProductType("bullet");
        block.setUuid(getUuid());
        block.setDescription(getDescription());
        block.setPrice(getPrice());
        if (caliber != null && !caliber.equals("")) {
            block.addCharacteristics("bullet.caliber", caliber);
        }
        if (type != null && !type.equals("")) {
            block.addCharacteristics("bullet.type", type);
        }
        if (qty != null && qty > 0) {
            block.addCharacteristics("bullet.qty", String.valueOf(qty));
        }
        return block;
    }
}
