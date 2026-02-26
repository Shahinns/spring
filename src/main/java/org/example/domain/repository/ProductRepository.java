package org.example.domain.repository;

import org.example.domain.Product;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProductRepository {
    List<Product> getAllProducts();

    Product getProductById(String productID);
    List<Product> getProductsByCategory(String category);

    List<Product> getProductsByPrice(int low, int high);

    Set<Product> getProductsByFilter(Map<String, List<String>> filterParams);

    void addProduct(Product product);

}
