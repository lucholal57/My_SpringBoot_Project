package com.SpringBootProject.app.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String description;
    /*
    Fetch sirve para definir si muestra o no los datos de la categoria asociada al producto en este caso.
    LAZY no trae los datos de categoria.
    EAGER si trae los datos de la categoria asociada.
     */
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
    /*
    La clase Big Decimal sirve para trabajar con monedas. Tiene mas precision que un Double.
     */
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private Integer qty;
    @Column(nullable = false)
    private Date date_created;
    @Column(nullable = true)
    private Date date_deleted;

    public ProductEntity() {
    }

    public ProductEntity(Long id, String name, String description, CategoryEntity category, BigDecimal price, Integer qty, Date date_created, Date date_deleted) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.qty = qty;
        this.date_created = date_created;
        this.date_deleted = date_deleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public Date getDate_deleted() {
        return date_deleted;
    }

    public void setDate_deleted(Date date_deleted) {
        this.date_deleted = date_deleted;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", qty=" + qty +
                ", date_created=" + date_created +
                ", date_deleted=" + date_deleted +
                '}';
    }
}
