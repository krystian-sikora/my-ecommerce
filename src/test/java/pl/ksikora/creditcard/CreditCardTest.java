package pl.ksikora.creditcard;

import org.junit.Test;
import pl.ksikora.creditcard.exceptions.CreditBelowLimitException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CreditCardTest {

    @Test
    public void itAllowsToAssignCreditLimit() {
        // Arrange
        CreditCard card1 = new CreditCard("1234-5678");
        CreditCard card2 = new CreditCard("1234-5678");
        // Act
        card1.assignLimit(BigDecimal.valueOf(1000));
        card2.assignLimit(BigDecimal.valueOf(1100));
        // Assert
        assertEquals(BigDecimal.valueOf(1000), card1.getCurrentBalance());
        assertEquals(BigDecimal.valueOf(1100), card2.getCurrentBalance());
    }

    @Test
    public void itDeniesLimitsBelow100() {
        CreditCard card1 = new CreditCard("1234-5678");
        assertThrows(
                CreditBelowLimitException.class,
                () -> card1.assignLimit(BigDecimal.valueOf(99))
        );
        assertDoesNotThrow(() -> card1.assignLimit(BigDecimal.valueOf(100)));
    }
}
