package pl.ksikora.creditcard;

import org.junit.Test;
import pl.ksikora.creditcard.exceptions.CreditReassignmentException;
import pl.ksikora.creditcard.exceptions.CreditBelowTresholdException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CreditCardTest {

    @Test
    public void itAllowsToAssignCreditLimit() {
        // Arrange
        CreditCard card1 = new CreditCard("1234-5678");
        CreditCard card2 = new CreditCard("1234-5678");
        // Act
        card1.assignCredit(BigDecimal.valueOf(1000));
        card2.assignCredit(BigDecimal.valueOf(1100));
        // Assert
        assertEquals(BigDecimal.valueOf(1000), card1.getCurrentBalance());
        assertEquals(BigDecimal.valueOf(1100), card2.getCurrentBalance());
    }

    @Test
    public void itDeniesLimitsBelow100() {
        CreditCard card1 = new CreditCard("1234-5678");
        assertThrows(
                CreditBelowTresholdException.class,
                () -> card1.assignCredit(BigDecimal.valueOf(99))
        );
        assertDoesNotThrow(() -> card1.assignCredit(BigDecimal.valueOf(100)));
    }

    @Test
    public void itCantAssignLimitTwice() {
        CreditCard card1 = new CreditCard("1234-5678");

        card1.assignCredit(BigDecimal.valueOf(1000));

        assertThrows(
                CreditReassignmentException.class,
                () -> card1.assignCredit(BigDecimal.valueOf(1100))
        );
    }
}
