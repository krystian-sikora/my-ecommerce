package pl.ksikora;

import org.hibernate.type.descriptor.sql.JdbcTypeJavaClassMappings;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import pl.ksikora.productcatalog.Product;

import java.util.UUID;

@SpringBootTest
public class JdbcPlaygroundTest {

    @Autowired
    JdbcTemplate db;

    @BeforeEach
    void setup() {
        db.execute("DROP TABLE `products` IF EXISTS");
        db.execute("CREATE TABLE `products` (" +
                "`id` VARCHAR(100)," +
                "`name` VARCHAR(100)," +
                "PRIMARY KEY(id)" +
                ")");
    }
    @Test
    public void testIt() {
        String result = db.queryForObject("select 'Hello world'", String.class);

        assert result.equals("Hello world");
    }
    @Test
    public void insert() {
        Product product = new Product(UUID.randomUUID().toString(), "Lego", "Nice set");
        db.update("INSERT INTO `products` (`id`,`name`,`desc`) values (?, ?, ?)",
            product.getId(),
            product.getName(),
            product.getDescription()
        );

        int productsCount = db.queryForObject("select count(*) from products", Integer.class);
        assert productsCount == 1;
    }

    @Test
    public void select() {
        Product product = new Product(UUID.randomUUID().toString(), "Lego", "Nice set");
        db.update("INSERT INTO `products` (`id`,`name`,`desc`) values (?, ?, ?)",
                product.getId(),
                product.getName(),
                product.getDescription()
        );

        String sql = "select * from products where id = ?";
        db.queryForObject(sql, new Object[]{product.getId()}, (rs, i) -> {
            return new Product(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("description"));
        });
    }

}
