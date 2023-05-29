package pl.ksikora.productcatalog;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private final String uuid;
    private final String name;
    private final String description;
    private BigDecimal price;
    private String image;
    private boolean online;



    public Product(String uuid, String name, String description) {
        this.uuid = uuid.toString();
        this.name = name;
        this.description = description;
    }
    public void changePrice(BigDecimal newPrice) {
        this.price = newPrice;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public String getId() {
        return uuid;
    }
    public  UUID getUUID() {
        return UUID.fromString(uuid);
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String imageKey) {
        image = imageKey;
    }
    public boolean getOnline() {
        return online;
    }
    public void setOnline(boolean online) {
        this.online = online;
    }
}
