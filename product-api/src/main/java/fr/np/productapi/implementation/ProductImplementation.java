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

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> saveAll(List<Product> products) {
        return productRepository.saveAll(products);
    }

    @Override
    public Product findById(int id) {
        return productRepository.findProductById(id);
    }

    @Override
    public Product findByCode(String code) {
        return productRepository.findProductByCode(code);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(int id, Product product) {

        Product productToUpdate = productRepository.findProductById(id);

        if (productToUpdate != null) {

            productToUpdate = product;

            return productRepository.save(productToUpdate);
        }

        return new Product();
    }

    @Override
    public void delete(int id) {
        Product product = productRepository.findProductById(id);
        if (product != null) {
            productRepository.delete(product);
        }
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }

}
