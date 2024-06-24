package fr.np.productapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.np.productapi.models.Product;
import fr.np.productapi.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class ProductApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApiApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(ProductService productService) {

        productService.deleteAll();

        return args -> {
            // Lire le fichier JSON depuis le classpath
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Product>> typeReference = new TypeReference<List<Product>>() {};
            ClassPathResource resource = new ClassPathResource("db/products.json");
            try (InputStream inputStream = resource.getInputStream()) {
                List<Product> products = mapper.readValue(inputStream, typeReference);
                productService.saveAll(products);
                System.out.println("Products saved!");
            } catch (IOException e) {
                System.out.println("Unable to save products: " + e.getMessage());
            }
        };
    }

}
