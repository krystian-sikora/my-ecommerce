package pl.ksikora.sales;

import org.junit.Test;

import java.util.UUID;

public class CollectingProductsTest {
    @Test
    public void itAllowsToAddProduct() {
        // Arrange
        Sales sales = thereIsSalesModule();
        String product1 = thereIsProduct();
        String customerId = thereIsCustomer("Krystian");
        // Act
        sales.addToCart(customerId, product1);
        // Assert
        assertThereIsXProductsInCustomerCart(1, customerId);
    }

    private void assertThereIsXProductsInCustomerCart(int totalProductsQuantity, String customerId) {

    }

    private String thereIsCustomer(String id) {
        return id;
    }

    private String thereIsProduct() {
        return UUID.randomUUID().toString();
    }

    private Sales thereIsSalesModule() {
        return new Sales(new CartStorage(), new ProductDetailsProvider());
    }
}
