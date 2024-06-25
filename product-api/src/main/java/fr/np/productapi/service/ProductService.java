package fr.np.productapi.service;

import fr.np.productapi.models.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    List<Product> saveAll(List<Product> products);

    Product findById(int id);

    Product findByCode(String code);

    Product save(Product product);

    Product update(int id, Product product);

    void delete(int id);

    void deleteAll();

}
