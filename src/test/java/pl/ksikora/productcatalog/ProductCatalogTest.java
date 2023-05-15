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
