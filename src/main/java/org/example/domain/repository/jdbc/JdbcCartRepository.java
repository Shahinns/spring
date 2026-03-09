package org.example.domain.repository.jdbc;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.domain.CartItem;
import org.example.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.example.domain.Cart;
import org.example.domain.repository.CartRepository;

@Repository
public class JdbcCartRepository implements CartRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Cart create(Cart cart) {
        String sql = "INSERT INTO carts (cart_id) VALUES (?)";
        jdbcTemplate.update(sql, cart.getCartId());
        return cart;
    }

    @Override
    public Cart read(String cartId) {
        String cartSql = "SELECT cart_id FROM carts WHERE cart_id = ? ";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(cartSql, cartId);
        if(rows.isEmpty()) {
            return null;
        }

        Cart cart = new Cart(cartId);

        String itemsSql = "SELECT ci.*, p.name, p.description, p.unit_price, " +
                "p.manufacturer, p.category, p.units_in_stock " +
                "FROM cart_items ci " +
                "JOIN products p ON ci.product_id = p.product_id " +
                "WHERE ci.cart_id = ?";

        List<Map<String, Object>> itemRows = jdbcTemplate.queryForList(itemsSql, cartId);

        Map<String, CartItem> cartItems = new HashMap<>();
        for(Map<String, Object> row : itemRows) {
            String productId = (String) row.get("product_id");

            Product product = new Product();
            product.setProductId(productId);
            product.setName((String) row.get("name"));
            product.setDescription((String) row.get("description"));
            product.setUnitPrice((BigDecimal) row.get("unit_price"));
            product.setManufacturer((String) row.get("manufacturer"));
            product.setCategory((String) row.get("category"));
            product.setUnitsInStock(((Number) row.get("units_in_stock")).intValue());

            CartItem item = new CartItem(product);
            item.setQuantity(((Number) row.get("quantity")).intValue());
            cartItems.put(productId, item);
        }

        cart.setCartItems(cartItems);
        cart.updateGrandTotal();
        return cart;
    }

    @Override
    public void update(String cartId, Cart cart) {
        String deleteSql = "DELETE FROM cart_items WHERE cart_id = ?";
        jdbcTemplate.update(deleteSql, cartId);

        String insertSql = "INSERT INTO cart_items (cart_id, product_id, quantity, total_price) VALUES (?, ?, ?, ?)";
        for(CartItem item : cart.getCartItems().values()) {
            jdbcTemplate.update(insertSql,
                    cartId,
                    item.getProduct().getProductId(),
                    item.getQuantity(),
                    item.getTotalPrice()
            );
        }
    }

    @Override
    public void delete(String cartId) {
        String deleteItemsSql = "DELETE FROM cart_items WHERE cart_id = ?";
        jdbcTemplate.update(deleteItemsSql, cartId);

        String deleteCartSql = "DELETE FROM carts WHERE cart_id = ?";
        jdbcTemplate.update(deleteCartSql, cartId);
    }
}