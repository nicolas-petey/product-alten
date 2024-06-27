package fr.np.productapi.service;

import fr.np.productapi.models.Product;
import fr.np.productapi.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
public class ProductServiceTests {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    void shouldSaveProductWhenNotExists() {
        Product product = new Product();
        product.setCode("123");

        when(productRepository.findProductByCode(product.getCode())).thenReturn(null);

        productService.save(product);

        verify(productRepository, times(1)).save(product);
    }

    @Test
    void shouldNotSaveProductWhenExists() {
        Product product = new Product();
        product.setCode("1");

        when(productRepository.findProductByCode(product.getCode())).thenReturn(product);

        productService.save(product);

        verify(productRepository, times(0)).save(product);
    }



}
