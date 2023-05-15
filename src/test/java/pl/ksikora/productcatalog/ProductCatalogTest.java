package pl.ksikora.productcatalog;

import org.junit.Test;

import java.util.List;

public class ProductCatalogTest {
    @Test
    public void itAllowsToListMyProducts() {
        // Arrange
        ProductCatalog catalog = thereIsProductCatalog();
        // Act
        List<Product> products = catalog.allProducts();
        // Assert
        assertListIsEmpty(products);
    }

    private void assertListIsEmpty(List<Product> products) {
        assert 0 == products.size();
    }

    private ProductCatalog thereIsProductCatalog() {
        return new ProductCatalog();
    }

    @Test
    public void itAllowsToAddProduct() {
        // Arrange
        ProductCatalog catalog = thereIsProductCatalog();
        // Act
        String productId = catalog.addProduct("book", "nice book");
        // Assert
        List<Product> products = catalog.allProducts();
        assert 1 == products.size();
    }

    @Test
    public void itAllowsToLoadProductDetails() {
        ProductCatalog catalog = thereIsProductCatalog();

        String productId = catalog.addProduct("book", "nice book");

        Product loadedProduct = catalog.loadById(productId);
        assert loadedProduct.getId().equals(productId);
    }

    @Test
    public void itAllowsToChangePrice() {

    }

    @Test
    public void itAllowsToChangeImage() {

    }

    @Test
    public void itAllowsToPublishProduct() {

    }
}
