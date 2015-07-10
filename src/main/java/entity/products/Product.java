/**
 * Created by Admin on 21.05.2015.
 * The Basic class from Product
 */

package entity.products;

public abstract class Product {
    private String type;
    private Double price;

    public Product(String name, Double price) {
        this.type = name;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public Double getPrice() {
        return price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
