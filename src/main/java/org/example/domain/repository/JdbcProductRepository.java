package org.example.domain.repository;

import org.example.domain.Product;
import org.example.domain.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class JdbcProductRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM products";
        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

    @Override
    public Product getProductById(String productID) {
        String sql = "SELECT * FROM products WHERE product_id = ?";
        List<Product> products = jdbcTemplate.query(sql, new ProductRowMapper(), productID);
        return products.isEmpty() ? null : products.get(0);
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        String sql = "SELECT * FROM products WHERE category = ?";
        return jdbcTemplate.query(sql, new ProductRowMapper(), category);
    }

    @Override
    public List<Product> getProductsByPrice(int low, int high) {
        String sql = "SELECT * FROM products WHERE unit_price BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, new ProductRowMapper(), low, high);
    }

    @Override
    public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {

        StringBuilder sql = new StringBuilder("SELECT * FROM products WHERE 1=1");
        List<Object> params = new ArrayList<>();

        // Category filter (multi-value support)
        if (filterParams.containsKey("category")) {
            List<String> categories = filterParams.get("category");
            sql.append(" AND category IN (");
            sql.append(String.join(",", Collections.nCopies(categories.size(), "?")));
            sql.append(")");
            params.addAll(categories);
        }

        // Manufacturer filter
        if (filterParams.containsKey("manufacturer")) {
            List<String> manufacturers = filterParams.get("manufacturer");
            sql.append(" AND manufacturer IN (");
            sql.append(String.join(",", Collections.nCopies(manufacturers.size(), "?")));
            sql.append(")");
            params.addAll(manufacturers);
        }

        // Product Condition filter
        if (filterParams.containsKey("condition")) {
            List<String> conditions = filterParams.get("condition");
            sql.append(" AND product_condition IN (");
            sql.append(String.join(",", Collections.nCopies(conditions.size(), "?")));
            sql.append(")");
            params.addAll(conditions);
        }

        List<Product> products = jdbcTemplate.query(
                sql.toString(),
                new ProductRowMapper(),
                params.toArray()
        );

        return new HashSet<>(products);
    }

    @Override
    public void addProduct(Product product) {

        String sql = "INSERT INTO products " +
                "(product_id, name, unit_price, description, manufacturer, category, " +
                "units_in_stock, units_in_order, discontinued, condition) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                product.getProductId(),
                product.getName(),
                product.getUnitPrice(),
                product.getDescription(),
                product.getManufacturer(),
                product.getCategory(),
                product.getUnitsInStock(),
                product.getUnitsInOrder(),
                product.isDiscontinued(),
                product.getCondition()
        );
    }
}