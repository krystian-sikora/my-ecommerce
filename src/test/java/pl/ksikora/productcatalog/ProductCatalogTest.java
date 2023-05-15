package pl.ksikora.productcatalog;

import org.junit.Test;
import pl.ksikora.productcatalog.exceptions.ProductCantBePublishedException;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        return new ProductCatalog(
                new HashMapProductStorage()
        );
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
        assert loadedProduct.getName().equals("book");
    }

    @Test
    public void itAllowsToChangePrice() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("book", "nice book");

        catalog.changePrice(productId, BigDecimal.valueOf(20.49));

        Product loadedProduct = catalog.loadById(productId);
        assertEquals(BigDecimal.valueOf(20.49), loadedProduct.getPrice());
    }

    @Test
    public void itAllowsToAssignImage() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("book", "nice book");

        catalog.assignImage(productId, "foo/boo/nice_image.jpeg");

        Product loadedProduct = catalog.loadById(productId);
        assertEquals("foo/boo/nice_image.jpeg", loadedProduct.getImage());
    }

    @Test
    public void itAllowsToPublishProduct() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("book", "nice book");
        catalog.changePrice(productId, BigDecimal.valueOf(20.49));
        catalog.assignImage(productId, "nice.jpeg");

        catalog.publishProduct(productId);

        List<Product> publishedProducts = catalog.allPublishedProducts();
        assertDoesNotThrow(() -> catalog.publishProduct(productId));
        assertEquals(1, publishedProducts.size());
    }

    @Test
    public void publicationIsPossibleWhenPriceAndImageAreDefined() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("book", "nice book");

        assertThrows(
                ProductCantBePublishedException.class,
                () -> catalog.publishProduct(productId)
        );
    }
}
