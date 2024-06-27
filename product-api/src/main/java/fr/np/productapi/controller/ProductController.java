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

    /**
     * Handles the GET request to retrieve all products.
     * It uses the findAll method from the ProductImplementation class to retrieve all products.
     *
     * @return List<Product> This returns a list of all products.
     */
    @GetMapping("/")
    public List<Product> findAll() {
        return productImplementation.findAll();
    }

    /**
     * Handles the GET request to retrieve a product by its id.
     * It uses the findById method from the ProductImplementation class to retrieve the product.
     *
     * @param id The id of the product to be retrieved.
     * @return Product This returns the product with the given id.
     */
    @GetMapping("/{id}")
    public Product findById(@PathVariable int id) {
        return productImplementation.findById(id);
    }

    /**
     * Handles the POST request to save a product.
     * It uses the save method from the ProductImplementation class to save the product.
     *
     * @param product The product to be saved.
     * @return Product This returns the saved product.
     */
    @PostMapping("/")
    public Product save(@RequestBody Product product) {
        return productImplementation.save(product);
    }

    /**
     * Handles the PATCH request to update a product by its id.
     * It uses the update method from the ProductImplementation class to update the product.
     *
     * @param id The id of the product to be updated.
     * @param product The product data to be updated.
     * @return Product This returns the updated product.
     */
    @PatchMapping("/{id}")
    public Product update(@PathVariable int id, @RequestBody Product product) {
        return productImplementation.update(id, product);
    }

    /**
     * Handles the DELETE request to delete a product by its id.
     * It uses the delete method from the ProductImplementation class to delete the product.
     *
     * @param id The id of the product to be deleted.
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        productImplementation.delete(id);
    }
}
