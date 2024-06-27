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


    /**
     * Initializes the application by loading products from a JSON file into the database.
     * This method is run at the start of the application.
     *
     * @param productService The service class for managing products.
     * @return CommandLineRunner This returns a CommandLineRunner object that can be run from the command line.
     */
    @Bean
    public CommandLineRunner init(ProductService productService) {

        return args -> {

            // Create a new ObjectMapper for mapping JSON to Java objects.
            ObjectMapper mapper = new ObjectMapper();

            // Create a TypeReference for a list of Product objects.
            TypeReference<List<Product>> typeReference = new TypeReference<>() {};

            // Load the JSON file from the classpath.
            ClassPathResource resource = new ClassPathResource("db/products.json");
            try (InputStream inputStream = resource.getInputStream()) {

                // Map the JSON file to a list of Product objects.
                List<Product> products = mapper.readValue(inputStream, typeReference);

                // Iterate over the list of products.
                for (Product product : products) {

                    // If the product does not already exist in the database, save it.
                    if (productService.findByCode(product.getCode()) == null) {
                        productService.save(product);
                    } else {
                        // If the product already exists in the database, print a message.
                        System.out.println("Product with code " + product.getCode() + " already exists.");
                    }
                }
                // Print a message when all products have been processed.
                System.out.println("Products processed!");
            } catch (IOException e) {
                // Print an error message if there was a problem processing the products.
                System.out.println("Unable to process products: " + e.getMessage());
            }
        };
    }

}
