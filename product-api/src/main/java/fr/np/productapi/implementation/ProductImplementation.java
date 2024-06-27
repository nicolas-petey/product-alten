package fr.np.productapi.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.np.productapi.models.Product;
import fr.np.productapi.repository.ProductRepository;
import fr.np.productapi.service.ProductService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class ProductImplementation implements ProductService {

    private final ProductRepository productRepository;

    public ProductImplementation(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Retrieves all products from the database.
     *
     * @return List<Product> This returns a list of all products in the database.
     */
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    /**
     * Saves a list of products to the database.
     *
     * @param products The list of products to be saved.
     * @return List<Product> This returns a list of the saved products.
     */
    @Override
    public List<Product> saveAll(List<Product> products) {
        return productRepository.saveAll(products);
    }

    /**
     * Retrieves a product by its id from the database.
     *
     * @param id The id of the product to be retrieved.
     * @return Product This returns the product with the given id.
     */
    @Override
    public Product findById(int id) {
        return productRepository.findProductById(id);
    }

    /**
     * Retrieves a product by its code from the database.
     *
     * @param code The code of the product to be retrieved.
     * @return Product This returns the product with the given code.
     */
    @Override
    public Product findByCode(String code) {
        return productRepository.findProductByCode(code);
    }

    /**
     * Saves a product to the database.
     *
     * @param product The product to be saved.
     * @return Product This returns the saved product.
     */
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    /**
     * Updates a product in the database.
     *
     * @param id The id of the product to be updated.
     * @param product The product data to be updated.
     * @return Product This returns the updated product if the product with the given id exists, otherwise returns a new product.
     */
    @Override
    public Product update(int id, Product product) {

        Product productToUpdate = productRepository.findProductById(id);

        if (productToUpdate != null) {

            productToUpdate = product;

            return productRepository.save(productToUpdate);
        }

        return new Product();
    }

    /**
     * Deletes a product by its id from the database.
     *
     * @param id The id of the product to be deleted.
     */
    @Override
    public void delete(int id) {
        Product product = productRepository.findProductById(id);
        if (product != null) {
            productRepository.delete(product);
        }
    }

    /**
     * Deletes all products from the database.
     */
    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }

}
