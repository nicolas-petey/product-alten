package fr.np.productapi.repository;

import fr.np.productapi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findProductById(Integer id);
}
