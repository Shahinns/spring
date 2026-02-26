package org.example.service;


import org.example.domain.Product;
import org.example.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public Product getProductById(String productID) {
        return productRepository.getProductById(productID);
    }
    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.getProductsByCategory(category);
    }

    public List<Product>  getProductsByPrice(int low, int high){

        return productRepository.getProductsByPrice(low , high);
    }

    public Set<Product> getProductsByFilter(Map<String, List<String>>
                                                    filterParams) {
        return productRepository.getProductsByFilter(filterParams);
    }

    public void addProduct(Product product) {
        productRepository.addProduct(product);




    }
}
