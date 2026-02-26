package org.example.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setProductId(rs.getString("product_id"));
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setUnitPrice(rs.getBigDecimal("unit_price"));
        product.setManufacturer(rs.getString("manufacturer"));
        product.setCategory(rs.getString("category"));
        product.setUnitsInStock(rs.getInt("units_in_stock"));
        product.setUnitsInOrder(rs.getInt("units_in_order"));
        product.setDiscontinued(rs.getBoolean("discontinued"));
        product.setCondition(rs.getString("condition"));
        return product;
    }
}