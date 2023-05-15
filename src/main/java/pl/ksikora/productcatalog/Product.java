package pl.ksikora.productcatalog;

import java.util.UUID;

public class Product {
    private final String uuid;
    private final String name;
    private final String description;

    public String getId() {
        return uuid;
    }
    public  UUID getUUID() {
        return UUID.fromString(uuid);
    }

    public Product(UUID uuid, String name, String description) {
        this.uuid = uuid.toString();
        this.name = name;
        this.description = description;
    }
}
