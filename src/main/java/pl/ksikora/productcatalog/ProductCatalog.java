package pl.ksikora.productcatalog;

import pl.ksikora.productcatalog.exceptions.ProductCantBePublishedException;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class ProductCatalog {
    private Map<String, Product> products;
    public ProductCatalog() {
        this.products = new HashMap<>();
    }
    public List<Product> allProducts() {
        return products.values()
                .stream()
                .collect(Collectors.toList());
    }

    public String addProduct(String name, String description) {
        Product newProduct = new Product(UUID.randomUUID(), name, description);
        products.put(newProduct.getId(), newProduct);

        return newProduct.getId();
    }

    public Product loadById(String productId) {
        return products.get(productId);
    }

    public void changePrice(String productId, BigDecimal newPrice) {
        Product product = loadById(productId);

        product.changePrice(newPrice);
    }

    public void assignImage(String productId, String imageKey) {
        Product product = loadById(productId);

        product.setImage(imageKey);
    }

    public void publishProduct(String productId) {
        Product product = loadById(productId);

        if (product.getImage() == null) {
            throw new ProductCantBePublishedException();
        }
        if (product.getPrice() == null) {
            throw new ProductCantBePublishedException();
        }

        product.setOnline(true);
    }

    public List<Product> allPublishedProducts() {
        return products.values()
                .stream()
                .filter(Product::getOnline)
                .collect(Collectors.toList());
    }
}
