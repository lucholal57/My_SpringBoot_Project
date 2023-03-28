package com.SpringBootProject.app.Entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "shoppingOrders")
public class ShoppingOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @OneToOne(fetch = FetchType.EAGER)
    private CartEntity cars;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private Date date_created;
    @Column(nullable = true)
    private Date date_deleted;

    public ShoppingOrderEntity() {
    }

    public ShoppingOrderEntity(Long id, String name, String description, CartEntity cars, BigDecimal price, Date date_created, Date date_deleted) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cars = cars;
        this.price = price;
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

    public CartEntity getCars() {
        return cars;
    }

    public void setCars(CartEntity cars) {
        this.cars = cars;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
        return "ShoppingOrderEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cars=" + cars +
                ", price=" + price +
                ", date_created=" + date_created +
                ", date_deleted=" + date_deleted +
                '}';
    }
}
