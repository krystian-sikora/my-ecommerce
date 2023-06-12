package pl.ksikora.sales;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import pl.ksikora.sales.cart.Cart;
import pl.ksikora.sales.cart.CartStorage;
import pl.ksikora.sales.offering.Offer;
import pl.ksikora.sales.offering.OfferCalculator;
import pl.ksikora.sales.productdetails.InMemoryProductDetailsProvider;
import pl.ksikora.sales.productdetails.ProductDetails;
import pl.ksikora.sales.offering.OfferLine;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CollectingProductsTest {
    private CartStorage cartStorage;
    private InMemoryProductDetailsProvider productDetails;
    @BeforeEach
    void setUp() {
        this.cartStorage = new CartStorage();
        this.productDetails = new InMemoryProductDetailsProvider();
    }


    @Test
    public void itAllowsToAddProduct() {
        // Arrange
        Sales sales = thereIsSalesModule();
        String product1 = thereIsProduct("My book", BigDecimal.valueOf(10.99));
        String customerId = thereIsCustomer("Krystian");
        // Act
        sales.addToCart(customerId, product1);
        // Assert
        assertThereIsXProductsInCustomerCart(1, customerId);
    }
    @Test
    public void itAllowAddProductToCartByMultipleCustomers() {
        // Arrange
        Sales sales = thereIsSalesModule();

        String productId1 = thereIsProduct("My book 1", BigDecimal.valueOf(10.99));
        String productId2 = thereIsProduct("My book 2", BigDecimal.valueOf(19.99));

        String customerId1 = thereIsCustomer("Krystian");
        String customerId2 = thereIsCustomer("Mikolaj");

        // Act
        sales.addToCart(customerId1, productId1);
        sales.addToCart(customerId1, productId2);

        sales.addToCart(customerId2, productId2);

        // Assert
        assertThereIsXProductsInCustomerCart(2, customerId1);
        assertThereIsXProductsInCustomerCart(1, customerId2);

    }

    @Test
    public void itGenerateOfferBasedOnCurrentCart() {
        // Arrange
        Sales sales = thereIsSalesModule();
        String productId1 = thereIsProduct("My book 1", BigDecimal.valueOf(10.10));
        String productId2 = thereIsProduct("My book 2", BigDecimal.valueOf(15.10));

        String customerId = thereIsCustomer("Krystian");

        // Act
        sales.addToCart(customerId, productId1);
        sales.addToCart(customerId, productId1);
        sales.addToCart(customerId, productId2);

        Offer offer = sales.getCurrentOffer(customerId);

        // Assert
        assertThat(offer.getTotal()).isEqualByComparingTo(BigDecimal.valueOf(45.30));
        assertThat(offer.getOrderLines())
                .hasSize(2);
        assertThat(offer.getOrderLines())
                .filteredOn(orderLine -> orderLine.getProductId().equals(productId1))
                .extracting(OfferLine::getQuantity)
                .first()
                .isEqualTo(2);

    }

    private void assertThereIsXProductsInCustomerCart(int totalProductsQuantity, String customerId) {
        Cart cart = cartStorage.load(customerId).get();

        assert cart.getItemsCount() == totalProductsQuantity;
    }

    private String thereIsCustomer(String id) {
        return id;
    }

    private String thereIsProduct(String productName, BigDecimal price) {
        String id = UUID.randomUUID().toString();
        productDetails.add(new ProductDetails(id, productName, price));
        return id;
    }

    private Sales thereIsSalesModule() {
        return new Sales(cartStorage, productDetails, new OfferCalculator(productDetails));
    }
}
