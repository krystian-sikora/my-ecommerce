package pl.ksikora.sales;

import java.math.BigDecimal;

public class Offer {
    public BigDecimal getTotal() {
        return total;
    }

    public Integer getItemsCount() {
        return itemsCount;
    }

    BigDecimal total;
    Integer itemsCount;

    public Offer() {
        this.total = BigDecimal.ZERO;
        this.itemsCount = 0;
    }
}
