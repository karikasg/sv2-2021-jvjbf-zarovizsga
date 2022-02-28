package webshop;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.awt.print.Book;
import java.sql.*;

public class ProductRepository {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;


    public ProductRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public long insertProduct(String productName, int price, int stock) {
        long id;
        try (Connection connection = dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement("insert into products (product_name, price, stock) values (?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, productName);
            stmt.setLong(2, price);
            stmt.setLong(3, stock);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            } else {
                throw new SQLException("No key has generated");
            }
        }
        catch (SQLException throwables) {
            throw new IllegalStateException("Cannot save: " + productName, throwables);
        }
        return id;
    }

    public Product findProductById(long id) {
        return jdbcTemplate.queryForObject("select * from products where id=?",
                (rs, rowNum)-> new Product(rs.getLong("id"), rs.getString("product_name"),
                        rs.getInt("price"), rs.getInt("stock")),
                id);
    }

    public void updateProductStock(long id, int amount) {
        jdbcTemplate.update("update products set stock = stock - ? where id = ?", amount, id);
    }
}
