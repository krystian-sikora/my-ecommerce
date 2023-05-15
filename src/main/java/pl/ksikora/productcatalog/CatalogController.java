package pl.ksikora.productcatalog;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CatalogController {
    private ProductCatalog catalog;

    CatalogController(ProductCatalog catalog) {
        this.catalog = catalog;
    }
    @GetMapping("/api/products")
    List<Product> allProducts() {
        return catalog.allPublishedProducts();
    }
}
