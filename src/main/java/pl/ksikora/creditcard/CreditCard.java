package pl.ksikora.creditcard;

import pl.ksikora.creditcard.exceptions.CreditReassignmentException;
import pl.ksikora.creditcard.exceptions.CreditBelowTresholdException;

import java.math.BigDecimal;

public class CreditCard {
    private BigDecimal balance;
    private BigDecimal credit;

    public CreditCard(String cardNumber) {}

    public void assignCredit(BigDecimal creditAmount) {
    if (isCreditAlreadyAssigned()) {
        throw new CreditReassignmentException();
    }
    if (isCreditBelowThreshold(creditAmount)) {
        throw new CreditBelowTresholdException();
    }
    this.balance = creditAmount;
    this.credit = creditAmount;
    }

    private static boolean isCreditBelowThreshold(BigDecimal creditAmount) {
        return creditAmount.compareTo(BigDecimal.valueOf(100)) < 0;
    }

    private boolean isCreditAlreadyAssigned() {
        return credit != null;
    }

    public void withdraw(BigDecimal value) {
        this.balance = balance.subtract(value);
    }
    public BigDecimal getCurrentBalance() {
        return balance;
    }
}
