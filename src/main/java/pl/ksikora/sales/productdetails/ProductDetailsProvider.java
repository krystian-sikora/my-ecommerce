package pl.ksikora.sales.productdetails;

import pl.ksikora.sales.productdetails.ProductDetails;

import java.util.Optional;

public interface ProductDetailsProvider {
    public Optional<ProductDetails> load(String productId);
}