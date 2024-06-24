package fr.np.productapi.models;

import fr.np.productapi.supplement.InventoryStatus;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "product")
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "idProduct", nullable = false, unique = true)
    private Integer id;

    @Column(name = "code", nullable = false, unique = true, length = 50)
    private String code;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Column(name = "image", nullable = false, length = 255)
    @Nullable
    private String image;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "category", nullable = false, length = 50)
    private String category;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "inventoryStatus", nullable = false)
    private InventoryStatus inventoryStatus;

    @Column(name = "rating", nullable = false)
    @Nullable
    private Integer rating;

    public Product() {
    }

    public Product(Integer id, String code, String name, String description, @Nullable String image, Integer price,
                   String category, Integer quantity, InventoryStatus inventoryStatus, @Nullable Integer rating) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
        this.inventoryStatus = inventoryStatus;
        this.rating = rating;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Nullable
    public String getImage() {
        return image;
    }

    public void setImage(@Nullable String image) {
        this.image = image;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public InventoryStatus getInventoryStatus() {
        return inventoryStatus;
    }

    public void setInventoryStatus(InventoryStatus inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    @Nullable
    public Integer getRating() {
        return rating;
    }

    public void setRating(@Nullable Integer rating) {
        this.rating = rating;
    }
}
