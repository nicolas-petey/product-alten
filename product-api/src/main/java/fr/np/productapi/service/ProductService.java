package fr.np.productapi.service;

import fr.np.productapi.models.Product;

import java.util.List;

public interface ProductService {

    /**
     * Retrieves all products from the database.
     *
     * @return List<Product> This returns a list of all products in the database.
     */
    List<Product> findAll();

    /**
     * Saves a list of products to the database.
     *
     * @param products The list of products to be saved.
     * @return List<Product> This returns a list of the saved products.
     */
    List<Product> saveAll(List<Product> products);

    /**
     * Retrieves a product by its id from the database.
     *
     * @param id The id of the product to be retrieved.
     * @return Product This returns the product with the given id.
     */
    Product findById(int id);

    /**
     * Retrieves a product by its code from the database.
     *
     * @param code The code of the product to be retrieved.
     * @return Product This returns the product with the given code.
     */
    Product findByCode(String code);

    /**
     * Saves a product to the database.
     *
     * @param product The product to be saved.
     * @return Product This returns the saved product.
     */
    Product save(Product product);

    /**
     * Updates a product in the database.
     *
     * @param id The id of the product to be updated.
     * @param product The product data to be updated.
     * @return Product This returns the updated product.
     */
    Product update(int id, Product product);

    /**
     * Deletes a product by its id from the database.
     *
     * @param id The id of the product to be deleted.
     */
    void delete(int id);

    /**
     * Deletes all products from the database.
     */
    void deleteAll();

}
