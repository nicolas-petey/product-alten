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
         * Cette méthode initialise l'application en chargeant des produits à partir d'un fichier JSON dans la base de données.
         * Elle utilise l'interface CommandLineRunner de Spring Boot qui indique qu'un bean doit être exécuté lorsqu'il est contenu dans une SpringApplication.
         * La méthode est annotée avec @Bean, ce qui signifie qu'une instance de CommandLineRunner sera créée, gérée par le conteneur IoC de Spring, et partagée à travers l'application.
         *
         * @param productService Le service utilisé pour les opérations sur les produits.
         * @return CommandLineRunner Cette méthode retourne une instance de CommandLineRunner qui charge les produits dans la base de données.
    */
    @Bean
    public CommandLineRunner init(ProductService productService) {

        return args -> {
            // Créer un ObjectMapper pour convertir le fichier JSON en objets Product.
            ObjectMapper mapper = new ObjectMapper();
            // Définir le type d'objet que l'ObjectMapper va créer.
            TypeReference<List<Product>> typeReference = new TypeReference<>() {};
            // Localiser le fichier JSON dans le classpath.
            ClassPathResource resource = new ClassPathResource("db/products.json");
            try (InputStream inputStream = resource.getInputStream()) {
                // Convertir le fichier JSON en une liste d'objets Product.
                List<Product> products = mapper.readValue(inputStream, typeReference);

                // Itérer sur la liste des produits.
                for (Product product : products) {
                    // Si le produit n'existe pas déjà dans la base de données, le sauvegarder.
                    if (productService.findByCode(product.getCode()) == null) {
                        productService.save(product);
                    } else {
                        System.out.println("Product with code " + product.getCode() + " already exists.");
                    }
                }
                System.out.println("Products processed!");
            } catch (IOException e) {
                System.out.println("Unable to process products: " + e.getMessage());
            }
        };
    }

}
