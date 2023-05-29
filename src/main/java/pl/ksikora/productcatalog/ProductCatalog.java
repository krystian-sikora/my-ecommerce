package pl.ksikora.productcatalog;

import pl.ksikora.productcatalog.exceptions.ProductCantBePublishedException;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class ProductCatalog {
    private ProductStorage productStorage;
    public ProductCatalog(ProductStorage productStorage) {
        this.productStorage = productStorage;
    }
    public List<Product> allProducts() {
        return productStorage.allProducts();
    }

    public String addProduct(String name, String description) {
        Product newProduct = new Product(UUID.randomUUID().toString(), name, description);
        productStorage.add(newProduct);

        return newProduct.getId();
    }

    public Product loadById(String productId) {
        return productStorage.loadById(productId);
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
        return productStorage.allPublishedProducts();
    }
}
