package pl.ksikora.sales;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class ReservationStorageTest {
    @Autowired
    ReservationRepository repository;
    @Test
    public void insert() {
        Reservation reservation = new Reservation("res-1234abcd", BigDecimal.valueOf(10.99),"payu/12345");

        repository.save(reservation);

        Reservation loaded = (Reservation) repository
                .findById("res-1234abcd")
                .get();
    }
    @Test
    public void select() {

    }
}
