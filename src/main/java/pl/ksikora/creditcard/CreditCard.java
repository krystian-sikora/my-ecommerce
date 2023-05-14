package pl.ksikora.creditcard;

import pl.ksikora.creditcard.exceptions.CreditBelowLimitException;

import java.math.BigDecimal;

public class CreditCard {
    BigDecimal balance;

    public CreditCard(String cardNumber) {}

    public void assignLimit(BigDecimal creditAmount) {
    if (creditAmount.compareTo(BigDecimal.valueOf(100)) < 0) {
        throw new CreditBelowLimitException();
    }
    this.balance = creditAmount;
    }

    public BigDecimal getCurrentBalance() {
        return balance;
    }
}
