package pl.ksikora.sales;

import pl.ksikora.sales.cart.Cart;
import pl.ksikora.sales.cart.CartStorage;
import pl.ksikora.sales.exceptions.NoSuchProductException;
import pl.ksikora.sales.offering.EveryNItemLineDiscountPolicy;
import pl.ksikora.sales.offering.Offer;
import pl.ksikora.sales.offering.OfferCalculator;
import pl.ksikora.sales.offering.TotalDiscountPolicy;
import pl.ksikora.sales.productdetails.ProductDetails;
import pl.ksikora.sales.productdetails.ProductDetailsProvider;

import java.math.BigDecimal;
import java.util.Optional;

public class Sales {
    private CartStorage cartStorage;
    private ProductDetailsProvider productDetailsProvider;
    private final OfferCalculator offerCalculator;

    public Sales(CartStorage cartStorage, ProductDetailsProvider productDetailsProvider, OfferCalculator offerCalculator) {
        this.cartStorage = cartStorage;
        this.productDetailsProvider = productDetailsProvider;
        this.offerCalculator = offerCalculator;
    }


    public void addToCart(String customerId, String productId) {
        Cart customerCart = loadCartForCustomer(customerId)
                .orElse(Cart.empty());

        ProductDetails product = loadProductDetails(productId)
                .orElseThrow(() -> new NoSuchProductException());

        customerCart.add(product.getId());

        cartStorage.addForCustomer(customerId, customerCart);
    }

    private Optional<ProductDetails> loadProductDetails(String productId) {
        return productDetailsProvider.load(productId);
    }

    private Optional<Cart> loadCartForCustomer(String customerId) {
        return cartStorage.load(customerId);
    }

    public Offer getCurrentOffer(String customerId) {
        Cart customerCart = loadCartForCustomer(customerId)
                .orElse(Cart.empty());

        Offer offer = this.offerCalculator.calculateOffer(
                customerCart.getCartItems(),
                new TotalDiscountPolicy(BigDecimal.valueOf(500), BigDecimal.valueOf(50)),
                new EveryNItemLineDiscountPolicy(5)
        );

        return offer;
    }
    public void acceptOffer() {}
}
