package org.example.controller;


import org.example.domain.Product;
import org.example.exception.NoProductsFoundUnderCategoryException;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/all")
    public String allProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @RequestMapping("/{category}")
    public String getProductsByCategory(Model
                                                model,@PathVariable("category") String category) {
        List<Product> products =productService.getProductsByCategory(category);
        if (products == null || products.isEmpty()) {
            throw new NoProductsFoundUnderCategoryException();
        }
        model.addAttribute("products", products);
        return "products";
    }


    @RequestMapping("/filter/price/{criteria}")
    public String filter(@MatrixVariable int low, @MatrixVariable int high, Model model) {

        model.addAttribute("products", productService.getProductsByPrice(low, high));
        return "products";
    }

    @RequestMapping("/filter/{ByCriteria}")
    public String getProductsByFilter(@MatrixVariable(pathVar= "ByCriteria") Map<String, List<String>> filterParams, Model model) {
        model.addAttribute("products", productService.getProductsByFilter(filterParams));
        return "products";
    }

    @RequestMapping("/product")
    public String getProductById(@RequestParam("id") String productId, Model model) {
        model.addAttribute("product", productService.getProductById(productId));
        return "product";
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAddNewProductForm(Model model) {
        Product newProduct = new Product();
        model.addAttribute("newProduct", newProduct);
        return "addProduct";
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddNewProductForm(@ModelAttribute("newProduct") Product productToBeAdded,
                                           BindingResult result , HttpServletRequest request) {


        String[] suppressedFields = result.getSuppressedFields();
        if (suppressedFields.length > 0) {
            throw new RuntimeException("Attempting to bind disallowed fields: "
                    + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }

        MultipartFile productImage = productToBeAdded.getProductImage();
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");

        if (productImage != null && !productImage.isEmpty()) {
            try {
                productImage.transferTo(new File(rootDirectory + "resources\\images\\" +
                        productToBeAdded.getProductId() + ".jpg"));
            } catch (Exception e) {
                throw new RuntimeException("Product Image saving failed", e);
            }
        }


        productService.addProduct(productToBeAdded);
        return "redirect:/products/all";
    }

    @InitBinder
    public void initialiseBinder(WebDataBinder binder) {
        binder.setAllowedFields("productId","name","unitPrice","description",
                "manufacturer","category","unitsInStock","productImage" , "condition");
    }


}

