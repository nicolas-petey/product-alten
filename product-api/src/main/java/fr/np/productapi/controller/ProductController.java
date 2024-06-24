package fr.np.productapi.controller;

import fr.np.productapi.implementation.ProductImplementation;
import fr.np.productapi.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductImplementation productImplementation;

    public ProductController(ProductImplementation productImplementation) {
        this.productImplementation = productImplementation;
    }

    @GetMapping("/")
    public List<Product> findAll() {
        return productImplementation.findAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable int id) {
        return productImplementation.findById(id);
    }

    @PostMapping("/")
    public Product save(@RequestBody Product product) {
        return productImplementation.save(product);
    }

    @PatchMapping("/{id}")
    public Product update(@PathVariable int id, @RequestBody Product product) {
        return productImplementation.update(id, product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        productImplementation.delete(id);
    }
}
